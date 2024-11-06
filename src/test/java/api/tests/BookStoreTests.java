package api.tests;

import api.steps.AccountApiSteps;
import api.steps.BookStoreApiSteps;
import api.utils.RestWrapper;
import helpers.auth.AuthApi;
import helpers.config.ApiBase;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static api.utils.spec.Specification.requestSpecification;
import static helpers.config.Config.cfg;
import static helpers.config.Endpoints.*;
import static helpers.config.TestData.wrongIsbn;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("API")
@Feature("Тесты методов Book Store")
@DisplayName("Тесты методов Book Store")
@Tag("API")
public class BookStoreTests extends ApiBase {

  BookStoreApiSteps bookStoreApiSteps = new BookStoreApiSteps();
  AuthApi authApi = new AuthApi();
  AccountApiSteps accountApiSteps = new AccountApiSteps();

  @Test
  @DisplayName("Удаление всех книг у пользователя")
  void deleteAllBooksFromAcc() {
    new RestWrapper()
            .delete(requestSpecification, book, "", bookStoreApiSteps.setPathForUserID())
            .shouldHaveStatusCode(204);
  }

  @Test
  @DisplayName("Добавление книги пользователю")
  void addBookToAcc() {
    new RestWrapper()
            .post(requestSpecification, book, bookStoreApiSteps.fillRegParamForAddBookRequest())
            .shouldHaveStatusCode(201)
            .shouldHaveJsonPath("books", not(emptyOrNullString()));
  }

  @Test
  @DisplayName("Поиск всех книг")
  void successfulBooksSearch() {
    new RestWrapper()
        .getNotAuth(requestSpecification, book, "")
        .shouldHaveStatusCode(200)
        .shouldHaveJsonPath("books.isbn[1]", containsString("9781449331818"));
  }

  @Test
  @DisplayName("Успешный поиск книги с определенным ISBN")
  void successfulBookSearch() {
    new RestWrapper()
        .getNotAuth(requestSpecification, bookISBN, bookStoreApiSteps.setPathForISBNSearch())
        .shouldHaveStatusCode(200)
        .shouldHaveJsonPath("isbn", containsString(cfg.getAvailableIsbn()));
  }

  @Test
  @DisplayName("Поиск книги, которой нет в Books Store")
  void bookSearchNotFound() {
    new RestWrapper()
            .getNotAuth(requestSpecification, bookISBN, bookStoreApiSteps.setPathForWrongISBNSearch())
            .shouldHaveStatusCode(400)
            .shouldHaveJsonPath("message", containsString("ISBN supplied is not available in Books Collection!"));
  }

  @Test
  @DisplayName("Удаление книги у пользователя")
  void deleteBookFromAccount() {
    new RestWrapper()
            .delete(requestSpecification, bookISBN,
                    bookStoreApiSteps.fillRegParamForDeleteBookRequest
                            (accountApiSteps.getIsbnBookFromAccount(), authApi.getUserId()), "")
            .shouldHaveStatusCode(204);
  }

  @Test
  @DisplayName("Удаление несуществующей книги у пользователя")
  void deleteNotAvailableBookFromAccount() {
    new RestWrapper()
            .delete(requestSpecification, bookISBN,
                    bookStoreApiSteps.fillRegParamForDeleteBookRequest(wrongIsbn, authApi.getUserId()), "")
            .shouldHaveStatusCode(400)
            .shouldHaveJsonPath("message",
                    containsString("ISBN supplied is not available in User's Collection!"));
  }

  @Test
  @DisplayName("Удаление книги без авторизации")
  void deleteBookFromAccountNotAuth() {
    new RestWrapper()
            .deleteNotAuth(requestSpecification, bookISBN,
                    bookStoreApiSteps.fillRegParamForDeleteBookRequest(accountApiSteps.getIsbnBookFromAccount(), authApi.getUserId()), "")
            .shouldHaveStatusCode(401)
            .shouldHaveJsonPath("message",
                    containsString("User not authorized!"));
  }
}
