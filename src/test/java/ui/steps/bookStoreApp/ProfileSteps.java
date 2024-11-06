package ui.steps.bookStoreApp;

import static helpers.config.Endpoints.profileUrl;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

import helpers.auth.AuthApi;
import ui.pages.bookStoreApp.ProfilePage;
import ui.steps.mainPage.MainPageSteps;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;

public class ProfileSteps {

  MainPageSteps mainPageSteps = new MainPageSteps();
  BookStoreSteps bookStoreSteps = new BookStoreSteps();
  ProfilePage profilePage = new ProfilePage();
  AuthApi authApi = new AuthApi();

  @Step("Перейти на страницу Profile")
  public void goToProfilePage() {
    Selenide.clearBrowserCookies();
    mainPageSteps.goToBookStorePage();
    bookStoreSteps.goToProfilePage();
  }

  @Step("Проверить текущий URL")
  public void checkCurrentURL(){
    webdriver().shouldHave(url(profileUrl));
  }

  @Step("Ввести куки авторизации")
  public void addAuthCookie(){
    String authCookieTokenKey = "token";
    String authCookieExpiresKey = "expires";
    String authCookieUserIDKey = "userID";

    String authCookieUserIDValue = authApi.getUserId();
    String authCookieTokenValue = authApi.getToken();
    String authCookieExpiresValue = authApi.getTokenExpires();

    Cookie authCookieToken = new Cookie(authCookieTokenKey, authCookieTokenValue);
    Cookie authCookieExpires = new Cookie(authCookieExpiresKey, authCookieExpiresValue);
    Cookie authCookieUserID = new Cookie(authCookieUserIDKey, authCookieUserIDValue);

    getWebDriver().manage().addCookie(authCookieToken);
    getWebDriver().manage().addCookie(authCookieExpires);
    getWebDriver().manage().addCookie(authCookieUserID);
    getWebDriver().navigate().refresh();
  }

  @Step("Деавторизоваться")
  public void logOut() {
    profilePage.logOutBtn().click();
  }

  @Step("Проверить имя пользователя")
  public void checkUserName(String name) {
    profilePage.userNameLabel().shouldHave(text(name));
  }

  @Step("Проверить имя пользователя")
  public void checkNotLoggingLabel() {
    profilePage.notLoggingLabel().shouldHave(text("Currently you are not logged into the Book Store "
        + "application, please visit the login page to enter or register page to register yourself."));
  }

  @Step("Ввести текст в поле поиска")
  public void fillSearchBookInput(String book) {
    profilePage.searchBookInput().sendKeys(book);
  }

  @Step("Ввести текст в поле поиска")
  public void checkTableLabel() {
    profilePage.emptyTableLabel();
  }
}
