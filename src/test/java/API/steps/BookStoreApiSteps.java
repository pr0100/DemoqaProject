package API.steps;

import static helpers.config.Endpoints.book;
import static API.utils.spec.BookStoreSpec.bookStoreRequestSpec;
import static API.utils.spec.BookStoreSpec.booksResponseSpec;
import static API.utils.spec.BookStoreSpec.deleteBookSpec;
import static API.utils.spec.BookStoreSpec.errorDeleteBookSpec;
import static API.utils.spec.BookStoreSpec.errorNotAuthSpec;
import static helpers.config.TestData.getElemArrayBooks;
import static helpers.config.TestData.wrongIsbn;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import helpers.auth.AuthApi;
import API.utils.Responses;
import API.utils.models.AddBookRequestModel;
import API.utils.models.BookRequestModel;
import API.utils.models.DeleteBookModel;
import API.utils.models.ErrorResponseModel;
import API.utils.models.IsbnModel;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;

public class BookStoreApiSteps {

  AuthApi authApi = new AuthApi();
  Responses responses = new Responses();
  AccountApiSteps accountApiSteps = new AccountApiSteps();

  private List<IsbnModel> getRandomListAvailableIsbn(int number) {
      List<IsbnModel> isbnList = new ArrayList<>();
      for (int i = 0; i < number; i++){
        IsbnModel isbn = new IsbnModel();
        isbn.setIsbn(responses.searchBooks().path("books[" + getElemArrayBooks() + "].isbn"));
        isbnList.add(isbn);
      }
      return isbnList;
  }

  private String getRandomAvailableIsbn() {
    IsbnModel isbn = new IsbnModel();
    isbn.setIsbn(responses.searchBooks().path("books[" + getElemArrayBooks() + "].isbn"));
    return isbn.getIsbn();
  }

  private AddBookRequestModel fillRegParamForAddBookRequest() {
    AddBookRequestModel regParams = new AddBookRequestModel();
    regParams.setUserId(authApi.getLoginResponse().path("userId"));
    regParams.setCollectionOfIsbns(getRandomListAvailableIsbn(getElemArrayBooks()));
    return regParams;
  }

  private DeleteBookModel fillRegParamForDeleteBookRequest(String userID) {
    DeleteBookModel regParams = new DeleteBookModel();
    regParams.setIsbn(accountApiSteps.getIsbnBookFromAccount(userID));
    regParams.setUserId(userID);
    return regParams;
  }

  private DeleteBookModel fillRegParamForErrorDeleteBookRequest(String userID) {
    DeleteBookModel regParams = new DeleteBookModel();
    regParams.setIsbn(wrongIsbn);
    regParams.setUserId(userID);
    return regParams;
  }

//  private Response searchBooks() {
//    return authApi.prepareNotAuthRequest(bookStoreRequestSpec, book)
//        .when()
//        .get("")
//        .then()
//        .statusCode(200)
//        .extract().response();
//  }
//
//  private Response searchBook(String path) {
//    return authApi.prepareNotAuthRequest(bookStoreRequestSpec, bookISBN)
//        .when()
//        .get(path)
//        .then()
//        .extract().response();
//  }

  @Step
  public void addBook() {
    authApi.
        prepareAuthRequest(bookStoreRequestSpec, book)
        .body(fillRegParamForAddBookRequest())
        .when()
        .post()
        .then()
        .spec(booksResponseSpec);
  }

  @Step
  public void deleteBooks() {
    authApi.
        prepareAuthRequest(bookStoreRequestSpec, book)
        .when()
        .delete("?UserId=" + authApi.getLoginResponse().path("userId"))
        .then()
        .log().all()
        .statusCode(204);
  }

  @Step
  public void searchAllBooks() {
    int booksSize = responses.searchBooks().path("books.size()");
    assertEquals(8, booksSize);
  }

  @Step
  public void searchISBNBook(){
    BookRequestModel getParam = new BookRequestModel();
    getParam.setIsbn(getRandomAvailableIsbn());
    Response response = responses.searchBook("?ISBN=" + getParam.getIsbn());
    String isbn = response.path("isbn");
    assertEquals(200, response.statusCode());
    assertEquals(getParam.getIsbn(), isbn);
  }

  @Step
  public void searchNotAvailableBook() {
    Response response = responses.searchBook("?ISBN=" + wrongIsbn);
    String message = response.path("message");
    assertEquals("ISBN supplied is not available in Books Collection!", message);
    assertEquals(400, response.statusCode());
  }

//  private Response deleteOneBook(DeleteBookModel params, ResponseSpecification spec) {
//    return authApi.prepareAuthRequest(bookStoreRequestSpec, bookISBN)
//        .body(params)
//        .when()
//        .delete()
//        .then()
//        .spec(spec)
//        .extract().response();
//  }
//
//  private Response deleteOneBookWithoutAuth(DeleteBookModel params, ResponseSpecification spec) {
//    return authApi.prepareNotAuthRequest(bookStoreRequestSpec, bookISBN)
//        .body(params)
//        .when()
//        .delete()
//        .then()
//        .spec(spec)
//        .extract().response();
//  }

  @Step
  public void deleteRealBook(String userID) {
    responses.deleteOneBook(fillRegParamForDeleteBookRequest(userID), deleteBookSpec);
  }

  @Step
  public void deleteNotRealBook(String userID) {
    ErrorResponseModel response =
        responses.deleteOneBook(fillRegParamForErrorDeleteBookRequest(userID), errorDeleteBookSpec)
        .as(ErrorResponseModel.class);
    assertEquals("ISBN supplied is not available in User's Collection!", response.getMessage());
  }

  @Step
  public void notAuthorized(String userID) {
    ErrorResponseModel response =
        responses.deleteOneBookWithoutAuth(fillRegParamForErrorDeleteBookRequest(userID), errorNotAuthSpec)
        .as(ErrorResponseModel.class);
    assertEquals("User not authorized!", response.getMessage());
  }

}
