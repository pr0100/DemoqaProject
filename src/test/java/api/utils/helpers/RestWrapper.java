package api.utils.helpers;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matcher;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;


public class RestWrapper {

    private Response response;
    protected static final Logger LOGGER = LogManager.getLogger();

    public RestWrapper post(RequestSpecification spec, String endpoint, Object body) {
        this.response = given(spec)
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .extract().response();
        LOGGER.info("post request done");
        return this;
    }

    public RestWrapper delete(RequestSpecification spec, HashMap<String,String> params, Object body,
        String path){
        this.response = given(spec)
                .queryParams(params)
                .body(body)
                .when()
                .delete(path)
                .then()
                .extract().response();
        LOGGER.info("delete request done");
        return this;
    }

    public RestWrapper get(RequestSpecification spec, HashMap<String, String> params, String path) {
        this.response = given(spec)
                .queryParams(params)
                .when()
                .get(path)
                .then()
                .extract().response();
        LOGGER.info("get request done");
        return this;
    }

    public RestWrapper getWithPath(RequestSpecification spec, HashMap<String, String> params, String path,
        String route) {
        this.response = given(spec)
            .queryParams(params)
            .when()
            .get(path, route)
            .then()
            .extract().response();
        LOGGER.info("get request done");
        return this;
    }

    @Step("Проверить код = {statusCode}")
    public RestWrapper shouldHaveStatusCode(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
        LOGGER.info("Status code checked '{}'", statusCode);
        return this;
    }

    @Step("Проверить {jsonPath} = {matcher}")
    public RestWrapper shouldHaveJsonPath(String jsonPath, Matcher<String> matcher) {
        response.then().assertThat().body(jsonPath, matcher);
        LOGGER.info("Json path checked '{}'", jsonPath);
        return this;
    }

    public List<String> getBodyFieldStringList(String jsonPath) {
        LOGGER.info("Got a list of field values by jsonPath '{}'", jsonPath);
        try {
            List<String> list = response.jsonPath().getList(jsonPath);
            if (list == null) {
                LOGGER.error("Error getting field value by jsonPath - null returned");
                return new ArrayList<>();
            }
            return list;
        } catch (Exception e) {
            LOGGER.error("Error getting field value by jsonPath");
            return new ArrayList<>();
        }
    }

    public String getBodyFieldString(String jsonPath) {
        LOGGER.info("Got a values by jsonPath '{}'", jsonPath);
        try {
            String bodyField = response.jsonPath().getString(jsonPath);
            if (bodyField == null) {
                LOGGER.error("Error getting value by jsonPath - null returned");
                return "Error";
            }
            return bodyField;
        } catch (Exception e) {
            LOGGER.error("Error getting value by jsonPath");
            return "Error";
        }
    }

}
