package ui.tests.bookStore;

import ui.steps.bookStoreApp.BookStoreSteps;
import ui.steps.mainPage.MainPageSteps;
import helpers.utils.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("ui")
@Feature("Тесты для вкладки Book Store")
@DisplayName("Тесты для вкладки Book Store")
@Tag("ui")
public class BookStoreTests extends BaseTest {

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
