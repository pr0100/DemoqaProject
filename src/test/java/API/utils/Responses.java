package API.utils;

import static helpers.config.Endpoints.book;
import static helpers.config.Endpoints.bookISBN;
import static API.utils.spec.BookStoreSpec.bookStoreRequestSpec;
import static io.restassured.RestAssured.given;

import helpers.auth.AuthApi;
import API.utils.models.DeleteBookModel;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;

public class Responses {

  AuthApi authApi = new AuthApi();

  public Response searchBooks() {
    return authApi.prepareNotAuthRequest(bookStoreRequestSpec, book)
        .when()
        .get("")
        .then()
        .statusCode(200)
        .extract().response();
  }

  public Response searchBook(String path) {
    return authApi.prepareNotAuthRequest(bookStoreRequestSpec, bookISBN)
        .when()
        .get(path)
        .then()
        .extract().response();
  }

  public Response deleteOneBook(DeleteBookModel params, ResponseSpecification spec) {
    return authApi.prepareAuthRequest(bookStoreRequestSpec, bookISBN)
        .body(params)
        .when()
        .delete()
        .then()
        .spec(spec)
        .extract().response();
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

}
