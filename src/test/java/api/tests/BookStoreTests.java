package api.tests;

import api.steps.AccountApiSteps;
import api.steps.BookStoreApiSteps;
import api.utils.RestWrapper;
import helpers.auth.AuthApi;
import helpers.config.ApiBase;
import helpers.config.TestData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import static api.utils.spec.Specification.authSpecification;
import static api.utils.spec.Specification.requestSpecification;
import static helpers.auth.AuthApi.getParams;
import static helpers.config.Config.cfg;
import static helpers.config.Endpoints.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Epic("api")
@Feature("Тесты методов Book Store")
@DisplayName("Тесты методов Book Store")
@Tag("api")
public class BookStoreTests extends ApiBase {

  BookStoreApiSteps bookStoreApiSteps = new BookStoreApiSteps();
  AuthApi authApi = new AuthApi();
  AccountApiSteps accountApiSteps = new AccountApiSteps();

  @Test
  @DisplayName("Удаление всех книг у пользователя")
  void deleteAllBooksFromAcc() {
    new RestWrapper()
            .delete(authSpecification, "", book + bookStoreApiSteps.setPathForUserID())
            .shouldHaveStatusCode(204);
  }

  @Test
  @DisplayName("Добавление книги пользователю")
  void addBookToAcc() {
    new RestWrapper()
            .post(authSpecification, book, bookStoreApiSteps.fillRegParamForAddBookRequest())
            .shouldHaveStatusCode(201)
            .shouldHaveJsonPath("books", not(emptyOrNullString()));
  }

  @Test
  @DisplayName("Поиск всех книг")
  void successfulBooksSearch() {
    new RestWrapper()
        .get(requestSpecification, book)
        .shouldHaveStatusCode(200)
        .shouldHaveJsonPath("books.isbn[1]", containsString("9781449331818"));
  }

  @Test
  @DisplayName("Успешный поиск книги с определенным ISBN")
  void successfulBookSearch() {
    new RestWrapper()
        .get(requestSpecification, bookISBN + bookStoreApiSteps.setPathForISBNSearch())
        .shouldHaveStatusCode(200)
        .shouldHaveJsonPath("isbn", containsString(cfg.getAvailableIsbn()));
  }

  @Test
  @DisplayName("Поиск книги, которой нет в Books Store")
  void bookSearchNotFound() {
    new RestWrapper()
            .get(requestSpecification, bookISBN + bookStoreApiSteps.setPathForWrongISBNSearch())
            .shouldHaveStatusCode(400)
            .shouldHaveJsonPath("message", containsString("ISBN supplied is not available in Books Collection!"));
  }

  @Test
  @DisplayName("Удаление книги у пользователя")
  void deleteBookFromAccount() {
    new RestWrapper()
            .delete(authSpecification,
                    bookStoreApiSteps.fillRegParamForDeleteBookRequest
                            (accountApiSteps.getIsbnBookFromAccount(), authApi.getUserId()), bookISBN)
            .shouldHaveStatusCode(204);
  }

  @Test
  @DisplayName("Удаление несуществующей книги у пользователя")
  void deleteNotAvailableBookFromAccount() {
    new RestWrapper()
            .delete(authSpecification,
                    bookStoreApiSteps.fillRegParamForDeleteBookRequest(TestData.getWrongIsbn(), authApi.getUserId()), bookISBN)
            .shouldHaveStatusCode(400)
            .shouldHaveJsonPath("message",
                    containsString("ISBN supplied is not available in User's Collection!"));
  }

  @Test
  @DisplayName("Удаление книги без авторизации")
  void deleteBookFromAccountNotAuth() {
    new RestWrapper()
            .delete(requestSpecification, "", bookISBN)
        .shouldHaveStatusCode(401)
            .shouldHaveJsonPath("message",
                    containsString("User not authorized!"));
  }
}
