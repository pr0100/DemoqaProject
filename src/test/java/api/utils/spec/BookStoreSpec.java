package api.utils.spec;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BookStoreSpec {

  public static RequestSpecification bookStoreRequestSpec = with()
      .filter(new AllureRestAssured())
      //.log4j2.xml().uri()
      //.log4j2.xml().body()
      //.log4j2.xml().headers()
      .contentType("application/json");

}

