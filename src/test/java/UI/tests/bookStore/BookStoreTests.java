package UI.tests.bookStore;

import UI.steps.bookStoreApp.BookStoreSteps;
import UI.steps.mainPage.MainPageSteps;
import helpers.config.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("UI")
@Feature("Тесты для вкладки Book Store")
@DisplayName("Тесты для вкладки Book Store")
@Tag("demoqaUI")
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
