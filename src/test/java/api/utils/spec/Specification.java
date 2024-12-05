package api.utils.spec;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.io.IoBuilder;


import helpers.auth.Authorization;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import java.io.PrintStream;


public class Specification {

  protected static final Logger LOGGER = LogManager.getLogger();

  private static final PrintStream logStream = IoBuilder.forLogger(LOGGER)
      .buildPrintStream();

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
        .filter(new RequestLoggingFilter(LogDetail.ALL, false, logStream, true))
        .filter(new ResponseLoggingFilter(LogDetail.ALL, false, logStream))
        .contentType(JSON);
  }

}

