package API.utils.spec;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class AccountSpec {

  public static RequestSpecification registrRequestSpec = with()
      .filter(new AllureRestAssured())
      .log().uri()
      .log().body()
      .log().headers()
      .contentType(JSON);

  public static RequestSpecification requestSpec = with()
      .filter(new AllureRestAssured())
      .log().uri()
      .log().body()
      .log().headers()
      .contentType("application/json");

  public static ResponseSpecification getResponseSpec = new ResponseSpecBuilder()
      .expectStatusCode(200)
      .log(STATUS)
      .log(BODY)
      .build();

  public static ResponseSpecification postResponseSpec = new ResponseSpecBuilder()
      .expectStatusCode(201)
      .log(STATUS)
      .log(BODY)
      .build();



}
