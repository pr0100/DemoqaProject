package api.utils.spec;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

import helpers.auth.Authorization;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.specification.RequestSpecification;

public class Specification {

  public static RequestSpecification requestSpecification() {
    return baseSpecification();
  }

  public static RequestSpecification authSpecification() {
    return baseSpecification()
        .auth().oauth2(Authorization.getInstance().getToken());
  }

  private static final AllureRestAssured allureRestAssured = new AllureRestAssured()
      .setRequestTemplate("custom-http-request.ftl")
      .setResponseTemplate("custom-http-response.ftl");

  private static RequestSpecification baseSpecification() {
    return with()
        .filter(allureRestAssured)
        .contentType(JSON);
  }

}

