package UI.tests.bookStore;

import UI.steps.bookStoreApp.BookStoreSteps;
import UI.steps.mainPage.MainPageSteps;
import helpers.config.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тесты для вкладки Book Store")
public class BookStoreTests extends TestBase {

  MainPageSteps mainPageSteps = new MainPageSteps();
  BookStoreSteps bookStoreSteps = new BookStoreSteps();

  @Test
  @DisplayName("Количество книг на странице")
  void changeRows() {
    mainPageSteps.goToBookStorePage();
    bookStoreSteps.selectSizeRows("5");
    bookStoreSteps.checkNumberBooks(5);
  }

}
