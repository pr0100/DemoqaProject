package ui.steps.bookStoreApp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ui.pages.bookStoreApp.BookStorePage;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import java.time.Duration;

public class BookStoreSteps {

  protected final Logger LOGGER = LogManager.getLogger();
  BookStorePage bookStorePage = new BookStorePage();

  @Step("Перейти на страницу Login")
  public void goToLoginPage() {
    bookStorePage.loginPageElement().shouldHave(Condition.visible).scrollTo().click();
  }

  @Step("Перейти на страницу Profile")
  public void goToProfilePage(){
    bookStorePage.profilePageElement().shouldHave(Condition.visible).scrollTo().click();
  }

  @Step("Выбрать количество строк в таблице")
  public void selectSizeRows(String value) {
    bookStorePage.sizeRows().selectOptionByValue(value);
    LOGGER.info("Size row selected");
  }

  @Step("Проверить количество строк в таблице")
  public void checkNumberBooks(int value) {
    bookStorePage.sizeRows().shouldBe(Condition.visible, Duration.ofSeconds(5));
    assertEquals(value, bookStorePage.numberBooks().size());
    LOGGER.info("Number of rows in the table is correct");
  }

}
