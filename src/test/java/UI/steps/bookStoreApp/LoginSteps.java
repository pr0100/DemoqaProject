package UI.steps.bookStoreApp;

import static helpers.config.Endpoints.loginUrl;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

import UI.pages.bookStoreApp.LoginPage;
import UI.steps.mainPage.MainPageSteps;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.conditions.CssValue;
import io.qameta.allure.Step;

public class LoginSteps {

  MainPageSteps mainPageSteps = new MainPageSteps();
  BookStoreSteps bookStoreSteps = new BookStoreSteps();
  LoginPage loginPage = new LoginPage();

  @Step("Перейти на страницу Login")
  public void goToLoginPage(){
    Selenide.clearBrowserCookies();
    mainPageSteps.goToBookStorePage();
    bookStoreSteps.goToLoginPage();
  }

  @Step("Заполнить поле Username")
  public void fillTheUserNameField(String name){
    loginPage.userNameElement().sendKeys(name);
  }

  @Step("Заполнить поле Password")
  public void fillThePasswordField(String password){
    loginPage.passwordElement().sendKeys(password);
  }

  @Step("Нажать кнопку Login")
  public void clickLoginBtnField(){
    loginPage.loginBtnElement().click();
  }

  @Step("Заполнить данные для авторизации и нажать кнопку Login")
  public void fillAuthDataField(String name, String password){
    fillTheUserNameField(name);
    fillThePasswordField(password);
    clickLoginBtnField();
  }

  @Step("Нажать кнопку New User")
  public void clickNewUserBtnField(){
    loginPage.newUserBtnElement().click();
  }

  @Step("Проверить цвет границ поля UserName")
  public void checkUserNameBorderColor(){
    loginPage.userNameElement().shouldHave(new CssValue("border-color", "rgb(220, 53, 69)"));
  }

  @Step("Проверить цвет границ поля Password")
  public void checkPasswordBorderColor(){
    loginPage.passwordElement().shouldHave(new CssValue("border-color", "rgb(220, 53, 69)"));
  }

  @Step("Проверить текущий URL")
  public void checkCurrentURL(){
    webdriver().shouldHave(url(loginUrl));
  }

  @Step("Проверить текст ошибки")
  public void checkErrorMessage(){
    loginPage.outputMessageElement().shouldHave(text("Invalid username or password!"));
  }
}
