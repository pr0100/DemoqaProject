package ui.steps.bookStoreApp;

import static helpers.config.Endpoints.LOGIN_URL;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ui.pages.bookStoreApp.LoginPage;
import ui.steps.mainPage.MainPageSteps;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.conditions.CssValue;
import io.qameta.allure.Step;

public class LoginSteps {

  MainPageSteps mainPageSteps = new MainPageSteps();
  BookStoreSteps bookStoreSteps = new BookStoreSteps();
  LoginPage loginPage = new LoginPage();
  protected static final Logger LOGGER = LogManager.getLogger();

  @Step("Перейти на страницу Login")
  public void goToLoginPage(){
    Selenide.clearBrowserCookies();
    mainPageSteps.goToBookStorePage();
    bookStoreSteps.goToLoginPage();
    LOGGER.info("Login page opened");
  }

  @Step("Нажать кнопку Login")
  public void clickLoginBtnField(){
    loginPage.loginBtnElement().scrollTo().click();
  }

  @Step("Заполнить данные для авторизации и нажать кнопку Login")
  public void fillAuthDataField(String name, String password){
    loginPage.userNameElement().sendKeys(name);
    loginPage.passwordElement().sendKeys(password);
    clickLoginBtnField();
    LOGGER.info("Auth data filled and login btn clicked");
  }

  @Step("Нажать кнопку New User")
  public void clickNewUserBtnField(){
    loginPage.newUserBtnElement().scrollTo().click();
    LOGGER.info("New User btn clicked");
  }

  @Step("Проверить цвет границ поля UserName")
  public void checkUserNameBorderColor(){
    loginPage.userNameElement().shouldHave(new CssValue("border-color", "rgb(220, 53, 69)"));
    LOGGER.info("UserName border color correct");
  }

  @Step("Проверить цвет границ поля Password")
  public void checkPasswordBorderColor(){
    loginPage.passwordElement().shouldHave(new CssValue("border-color", "rgb(220, 53, 69)"));
    LOGGER.info("Password border color correct");
  }

  @Step("Проверить текущий URL")
  public void checkCurrentURL(){
    webdriver().shouldHave(url(LOGIN_URL));
    LOGGER.info("URL correct");
  }

  @Step("Проверить текст ошибки")
  public void checkErrorMessage(){
    loginPage.outputMessageElement().shouldHave(text("Invalid username or password!"));
    LOGGER.info("Error message correct");
  }
}
