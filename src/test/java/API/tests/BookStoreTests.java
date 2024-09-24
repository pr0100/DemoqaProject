package API.tests;

import static helpers.config.TestData.defaultUserID;

import API.steps.BookStoreApiSteps;
import helpers.config.ApiBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тесты методов Book Store")
public class BookStoreTests extends ApiBase {

  BookStoreApiSteps bookStoreApiSteps = new BookStoreApiSteps();

  @Test
  @DisplayName("Удаление всех книг у пользователя")
  void deleteAllBooksFromAcc() {
    bookStoreApiSteps.deleteBooks();
  }

  @Test
  @DisplayName("Добавление книги пользователю")
  void addBookToAcc() {
    bookStoreApiSteps.addBook();
  }

  @Test
  @DisplayName("Поиск всех книг")
  void successfulBooksSearch() {
    bookStoreApiSteps.searchAllBooks();
  }

  @Test
  @DisplayName("Успешный поиск книги с определенным ISBN")
  void successfulBookSearch() {
    bookStoreApiSteps.searchISBNBook();
  }

  @Test
  @DisplayName("Поиск книги, которой нет в Books Store")
  void bookSearchNotFound() {
    bookStoreApiSteps.searchNotAvailableBook();
  }

  @Test
  @DisplayName("Удаление книги у пользователя")
  void deleteBookFromAccount() {
    bookStoreApiSteps.deleteRealBook(defaultUserID);
  }

  @Test
  @DisplayName("Удаление несуществующей книги у пользователя")
  void deleteNotAvailableBookFromAccount() {
    bookStoreApiSteps.deleteNotRealBook(defaultUserID);
  }

  @Test
  @DisplayName("Удаление книги без авторизации")
  void deleteBookFromAccountNotAuth() {
    bookStoreApiSteps.notAuthorized(defaultUserID);
  }
}
