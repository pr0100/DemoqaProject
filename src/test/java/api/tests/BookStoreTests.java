package api.tests;

import api.steps.AccountApiSteps;
import api.steps.BookStoreApiSteps;
import api.utils.templates.FillingModels;
import api.utils.wrapper.RestWrapper;
import helpers.auth.Authorization;
import helpers.utils.ApiBase;
import helpers.config.TestData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import static api.utils.spec.Specification.authSpecification;
import static api.utils.spec.Specification.requestSpecification;
import static helpers.config.Config.cfg;
import static helpers.config.Endpoints.*;
import static org.hamcrest.Matchers.*;

@Epic("api")
@Feature("Тесты методов Book Store")
@Tag("api")
public class BookStoreTests extends ApiBase {

  BookStoreApiSteps bookStoreApiSteps = new BookStoreApiSteps();
  Authorization authorization = Authorization.getInstance();
  AccountApiSteps accountApiSteps = new AccountApiSteps();
  FillingModels fillingModels = new FillingModels();

  @Test
  @DisplayName("Удаление всех книг у пользователя")
  void deleteAllBooksFromAcc() {
    new RestWrapper()
            .delete(authSpecification(), "", BOOK + bookStoreApiSteps.setPathForUserID())
            .shouldHaveStatusCode(204);
  }

  @Test
  @DisplayName("Добавление книги пользователю")
  void addBookToAcc() {
    new RestWrapper()
            .post(authSpecification(), BOOK, fillingModels.fillRegParamForAddBookRequest())
            .shouldHaveStatusCode(201)
            .shouldHaveJsonPath("books", not(emptyOrNullString()));
  }

  @Test
  @DisplayName("Поиск всех книг")
  void successfulBooksSearch() {
    new RestWrapper()
        .get(requestSpecification(), BOOK)
        .shouldHaveStatusCode(200)
        .shouldHaveJsonPath("books.isbn[1]", containsString("9781449331818"));
  }

  @Test
  @DisplayName("Успешный поиск книги с определенным ISBN")
  void successfulBookSearch() {
    new RestWrapper()
        .get(requestSpecification(), BOOK_ISBN + bookStoreApiSteps.setPathForISBNSearch())
        .shouldHaveStatusCode(200)
        .shouldHaveJsonPath("isbn", containsString(cfg.getAvailableIsbn()));
  }

  @Test
  @DisplayName("Поиск книги, которой нет в Books Store")
  void bookSearchNotFound() {
    new RestWrapper()
            .get(requestSpecification(), BOOK_ISBN + bookStoreApiSteps.setPathForWrongISBNSearch())
            .shouldHaveStatusCode(400)
            .shouldHaveJsonPath("message", containsString("ISBN supplied is not available in Books Collection!"));
  }

  @Test
  @DisplayName("Удаление книги у пользователя")
  void deleteBookFromAccount() {
    new RestWrapper()
            .delete(authSpecification(),
                fillingModels.fillRegParamForDeleteBookRequest
                            (accountApiSteps.getIsbnBookFromAccount(), authorization.getUserId()),
                BOOK_ISBN)
            .shouldHaveStatusCode(204);
  }

  @Test
  @DisplayName("Удаление несуществующей книги у пользователя")
  void deleteNotAvailableBookFromAccount() {
    new RestWrapper()
            .delete(authSpecification(),
                fillingModels.fillRegParamForDeleteBookRequest(TestData.getWrongIsbn(), authorization.getUserId()),
                BOOK_ISBN)
            .shouldHaveStatusCode(400)
            .shouldHaveJsonPath("message",
                    containsString("ISBN supplied is not available in User's Collection!"));
  }

  @Test
  @DisplayName("Удаление книги без авторизации")
  void deleteBookFromAccountNotAuth() {
    new RestWrapper()
            .delete(requestSpecification(), "", BOOK_ISBN)
            .shouldHaveStatusCode(401)
            .shouldHaveJsonPath("message",
                    containsString("User not authorized!"));
  }
}
