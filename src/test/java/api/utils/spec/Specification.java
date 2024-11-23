package api.utils.spec;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

import helpers.auth.Authorization;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.specification.RequestSpecification;

public class Specification {

  static Authorization authorization = new Authorization();

  private static final AllureRestAssured allureRestAssured = new AllureRestAssured()
          .setRequestTemplate("custom-http-request.ftl")
          .setResponseTemplate("custom-http-response.ftl");

  public static RequestSpecification requestSpecification = with()
      .filter(allureRestAssured)
      .contentType(JSON);

  public static RequestSpecification authSpecification = with()
      .filter(allureRestAssured)
      .auth().oauth2(authorization.getToken())
      .contentType(JSON);
}
