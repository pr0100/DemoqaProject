package api.utils;

import api.utils.models.DeleteBookModel;
import helpers.auth.AuthApi;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static api.utils.spec.BookStoreSpec.bookStoreRequestSpec;
import static helpers.config.Endpoints.*;
import static io.restassured.RestAssured.given;


public class RestWrapper {
    AuthApi authApi = new AuthApi();
    private Response response;

//    public RestWrapper post2() {
//        this.response = given()
//                .spec()
//                .body()
//                .post();
//        return this;
//    }

    public RestWrapper post(RequestSpecification spec, String endpoint, Object body) {
        this.response = authApi.prepareAuthRequest(spec, endpoint)
                .body(body)
                .when()
                .post()
                .then()
                .extract()
                .response();
        return this;
    }

    public RestWrapper postWithoutSpec(String endpoint, Object body) {
        this.response = given()
            .contentType("application/json")
            .body(body)
            .when()
            .post(endpoint)
            .then()
            .extract()
            .response();
        return this;
    }

    public RestWrapper delete(RequestSpecification spec, String endpoint, Object body, String path){
        this.response = authApi.prepareAuthRequest(spec, endpoint)
                .body(body)
                .when()
                .delete(path)
                .then()
                .extract().response();
        return this;
    }

    public RestWrapper deleteNotAuth(RequestSpecification spec, String endpoint, Object body, String path){
        this.response = authApi.prepareNotAuthRequest(spec, endpoint)
                .body(body)
                .when()
                .delete(path)
                .then()
                .extract().response();
        return this;
    }

    public RestWrapper getNotAuth(RequestSpecification spec, String endpoint, String path) {
        this.response = authApi.prepareNotAuthRequest(spec, endpoint)
                .when()
                .get(path)
                .then()
                .extract().response();
        return this;
    }

    public RestWrapper getAuth(RequestSpecification spec, String endpoint, String path) {
        this.response = authApi.prepareBearerAuthRequest(spec, endpoint)
                .when()
                .get(path)
                .then()
                .extract().response();
        return this;
    }

    public Response deleteOneBookWithoutAuth(DeleteBookModel params, ResponseSpecification spec) {
        return authApi.prepareNotAuthRequest(bookStoreRequestSpec, bookISBN)
                .body(params)
                .when()
                .delete()
                .then()
                .spec(spec)
                .extract().response();
    }

    public RestWrapper shouldHaveStatusCode(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
        return this;
    }

    public RestWrapper shouldHaveJsonPath(String jsonPath, Matcher<String> matcher) {
        response.then().assertThat().body(jsonPath, matcher);
        return this;
    }

    public int getBodyFieldInt(String jsonPath) {
        //logger.info("Получить значение поля по jsonPath '{}'", jsonPath);
        try {
            return response.jsonPath().getInt(jsonPath);
        } catch (Exception e) {
            //logger.error("Ошибка получения значения поля по jsonPath: ", e);
            return 0;
        }
    }

    public String getBody() {
        //logger.info("Получить полное тело ответа");
        try {
            String bodyField = response.asString();
            if (bodyField == null) {
                //logger.info("Тело ответа пустое");
                return "Error";
            }
            return bodyField;
        } catch (Exception e) {
            //logger.error("Ошибка получения тела ответа", e);
            return "Error";
        }
    }

    public List<String> getBodyFieldStringList(String jsonPath) {
        //logger.info("Получить список значений поля по jsonPath '{}'", jsonPath);
        try {
            List<String> list = response.jsonPath().getList(jsonPath);
            if (list == null) {
                //logger.error("Ошибка получения значения поля по jsonPath - возвращен null");
                return new ArrayList<>();
            }
            return list;
        } catch (Exception e) {
            //logger.error("Ошибка получения значения поля по jsonPath: ", e);
            return new ArrayList<>();
        }
    }

    public String getBodyFieldString(String jsonPath) {
        //logger.info("Получить значение поля по jsonPath '{}'", jsonPath);
        try {
            String bodyField = response.jsonPath().getString(jsonPath);
            if (bodyField == null) {
               // logger.error("Ошибка получения значения поля по jsonPath - возвращен null");
                return "Error";
            }
            return bodyField;
        } catch (Exception e) {
            //logger.error("Ошибка получения значения поля по jsonPath: ", e);
            return "Error";
        }
    }

}
