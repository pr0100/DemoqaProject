package ui.steps.mainPage;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ui.pages.mainPage.MainPage;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

public class MainPageSteps {

  MainPage mainPage = new MainPage();
  protected static final Logger LOGGER = LogManager.getLogger();

  @Step("Перейти на главную страницу")
  public void goToMainPage() {
    LOGGER.info("Main page opened");
    mainPage.demoqaLink().scrollTo().click();
  }

  @Step("Перейти на страницу Form")
  public void goToFormsPage(){
    LOGGER.info("Form page opened");
    mainPage.formsLink().shouldHave(Condition.visible).scrollTo().click();
  }

  @Step("Перейти на страницу Book Store")
  public void goToBookStorePage(){
    LOGGER.info("Book Store page opened");
    mainPage.bookStoreLink().shouldHave(Condition.visible).scrollTo().click();
  }

}
