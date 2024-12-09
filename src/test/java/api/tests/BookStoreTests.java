package api.tests;

import api.steps.ApiSteps;
import api.steps.HelpSteps;
import helpers.utils.ApiBase;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import static io.qameta.allure.Allure.step;
import static org.hamcrest.Matchers.*;

@Epic("api")
@Feature("Тесты методов Book Store")
@Tag("api")
public class BookStoreTests extends ApiBase {

  ApiSteps apiSteps = new ApiSteps();
  HelpSteps helpSteps = new HelpSteps();

  @Test
  @Severity(SeverityLevel.BLOCKER)
  @DisplayName("Добавление книги пользователю")
  void addBookToAcc() {
    String isbn = helpSteps.getIsbn();
    apiSteps.addBook(isbn)
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
    apiSteps.getBooks()
        .shouldHaveStatusCode(200)
        .shouldHaveJsonPath("books.isbn[1]", containsString("9781449331818"));
  }

  @Test
  @Severity(SeverityLevel.NORMAL)
  @DisplayName("Успешный поиск книги с определенным ISBN")
  void successfulBookSearch() {
    String isbn = helpSteps.getFirstIsbn();
    apiSteps.getBook()
        .shouldHaveStatusCode(200)
        .shouldHaveJsonPath("isbn", containsString(isbn));
  }

  @Test
  @Severity(SeverityLevel.MINOR)
  @DisplayName("Поиск книги, которой нет в Books Store")
  void bookSearchNotFound() {
    apiSteps.getWrongBook()
        .shouldHaveStatusCode(400)
        .shouldHaveJsonPath("message", containsString("ISBN supplied is not available in Books Collection!"));
  }

  @Test
  @Severity(SeverityLevel.NORMAL)
  @DisplayName("Удаление книги у пользователя")
  void deleteBookFromAccount() {
    step("Предварительные шаги", () -> {
      String isbn = helpSteps.getIsbn();
      apiSteps.addBook(isbn)
          .shouldHaveStatusCode(201);
    });
    apiSteps
        .deleteBook()
        .shouldHaveStatusCode(204);
  }

  @Test
  @Severity(SeverityLevel.NORMAL)
  @DisplayName("Удаление несуществующей книги у пользователя")
  void deleteNotAvailableBookFromAccount() {
    apiSteps.deleteWrongBook()
            .shouldHaveStatusCode(400)
            .shouldHaveJsonPath("message",
                    containsString("ISBN supplied is not available in User's Collection!"));
  }

  @Test
  @Severity(SeverityLevel.CRITICAL)
  @DisplayName("Удаление книги без авторизации")
  void deleteBookFromAccountNotAuth() {
    apiSteps.deleteBookError()
            .shouldHaveStatusCode(401)
            .shouldHaveJsonPath("message",
                    containsString("User not authorized!"));
  }
}
