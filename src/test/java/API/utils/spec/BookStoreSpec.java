package API.utils.spec;

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
      .log().uri()
      .log().body()
      .log().headers()
      .contentType("application/json");

  public static ResponseSpecification booksResponseSpec = new ResponseSpecBuilder()
      .expectStatusCode(201)
      .log(STATUS)
      .log(BODY)
      .build();

  public static ResponseSpecification deleteBookSpec = new ResponseSpecBuilder()
      .expectStatusCode(204)
      .log(STATUS)
      .log(BODY)
      .build();

  public static ResponseSpecification errorDeleteBookSpec = new ResponseSpecBuilder()
      .expectStatusCode(400)
      .log(STATUS)
      .log(BODY)
      .build();


  public static ResponseSpecification errorNotAuthSpec = new ResponseSpecBuilder()
      .expectStatusCode(401)
      .log(STATUS)
      .log(BODY)
      .build();

}

