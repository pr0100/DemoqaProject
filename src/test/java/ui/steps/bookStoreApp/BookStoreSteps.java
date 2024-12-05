package ui.steps.bookStoreApp;

import static com.codeborne.selenide.Condition.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ui.pages.bookStoreApp.BookStorePage;
import io.qameta.allure.Step;
import java.time.Duration;

public class BookStoreSteps {

  protected static final Logger LOGGER = LogManager.getLogger();
  BookStorePage bookStorePage = new BookStorePage();

  @Step("Перейти на страницу Login")
  public void goToLoginPage() {
    bookStorePage.loginPageElement().shouldHave(visible).scrollTo().click();
  }

  @Step("Перейти на страницу Profile")
  public void goToProfilePage(){
    bookStorePage.profilePageElement().shouldHave(visible).scrollTo().click();
  }

  @Step("Выбрать количество строк в таблице")
  public void selectSizeRows(String value) {
    bookStorePage.sizeRows().selectOptionByValue(value);
    LOGGER.info("Size row selected");
  }

  @Step("Проверить количество строк в таблице")
  public void checkNumberBooks(int value) {
    bookStorePage.sizeRows().shouldBe(visible, Duration.ofSeconds(5));
    assertEquals(value, bookStorePage.numberBooks().size());//переделать
    LOGGER.info("Number of rows in the table is correct");
  }

}
