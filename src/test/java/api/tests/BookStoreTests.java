package api.tests;

import api.steps.ApiSteps;
import api.utils.wrapper.RestWrapper;
import helpers.utils.ApiBase;
import helpers.config.TestData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import static api.utils.spec.Specification.requestSpecification;
import static helpers.config.Endpoints.*;
import static org.hamcrest.Matchers.*;

@Epic("api")
@Feature("Тесты методов Book Store")
@Tag("api")
public class BookStoreTests extends ApiBase {

  ApiSteps apiSteps = new ApiSteps();

  @Test
  @Severity(SeverityLevel.BLOCKER)
  @DisplayName("Добавление книги пользователю")
  void addBookToAcc() {
    apiSteps.addBook()
        .shouldHaveStatusCode(201)
        .shouldHaveJsonPath("books", not(emptyOrNullString()));
  }

  @Test
  @Severity(SeverityLevel.BLOCKER)
  @DisplayName("Удаление всех книг у пользователя")
  void deleteAllBooksFromAcc() {
    apiSteps.deleteAllBooks()
            .shouldHaveStatusCode(204);
  }

  @Test
  @Severity(SeverityLevel.MINOR)
  @DisplayName("Поиск всех книг")
  void successfulBooksSearch() {
    apiSteps.getInfo(requestSpecification(), "", BOOK)
        .shouldHaveStatusCode(200)
        .shouldHaveJsonPath("books.isbn[1]", containsString("9781449331818"));
  }

  @Test
  @Severity(SeverityLevel.NORMAL)
  @DisplayName("Успешный поиск книги с определенным ISBN")
  void successfulBookSearch() {
    String isbn = apiSteps.getFirstIsbn();
    apiSteps.getInfo(requestSpecification(), isbn, BOOK_ISBN)
        .shouldHaveStatusCode(200)
        .shouldHaveJsonPath("isbn", containsString(isbn));
  }

  @Test
  @Severity(SeverityLevel.MINOR)
  @DisplayName("Поиск книги, которой нет в Books Store")
  void bookSearchNotFound() {
    apiSteps.getInfo(requestSpecification(), TestData.getWrongIsbn(), BOOK_ISBN)
        .shouldHaveStatusCode(400)
        .shouldHaveJsonPath("message", containsString("ISBN supplied is not available in Books Collection!"));
  }

  @Test
  @Severity(SeverityLevel.NORMAL)
  @DisplayName("Удаление книги у пользователя")
  void deleteBookFromAccount() {
    apiSteps.addBook()
        .shouldHaveStatusCode(201);
    apiSteps
        .deleteBook(apiSteps.getIsbnBookFromAccount())
        .shouldHaveStatusCode(204);
  }

  @Test
  @Severity(SeverityLevel.NORMAL)
  @DisplayName("Удаление несуществующей книги у пользователя")
  void deleteNotAvailableBookFromAccount() {
    apiSteps.addBook()
            .shouldHaveStatusCode(201);
    apiSteps.deleteBook(TestData.getWrongIsbn())
            .shouldHaveStatusCode(400)
            .shouldHaveJsonPath("message",
                    containsString("ISBN supplied is not available in User's Collection!"));
  }

  @Test
  @Severity(SeverityLevel.CRITICAL)
  @DisplayName("Удаление книги без авторизации")
  void deleteBookFromAccountNotAuth() {
    new RestWrapper()
            .delete(requestSpecification(), "", BOOK_ISBN)
            .shouldHaveStatusCode(401)
            .shouldHaveJsonPath("message",
                    containsString("User not authorized!"));
  }
}
