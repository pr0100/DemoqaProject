package ui.steps.bookStoreApp;

import static api.utils.spec.Specification.requestSpecification;
import static helpers.config.Config.cfg;
import static helpers.config.Endpoints.login;
import static helpers.config.Endpoints.profileUrl;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;

import api.utils.RestWrapper;
import helpers.auth.AuthApi;
import io.restassured.response.Response;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
  protected static final Logger LOGGER = LogManager.getLogger();

  @Step("Перейти на страницу Profile")
  public void goToProfilePage() {
    Selenide.clearBrowserCookies();
    mainPageSteps.goToBookStorePage();
    bookStoreSteps.goToProfilePage();
    LOGGER.info("Profile page opened");
  }

  @Step("Проверить текущий URL")
  public void checkCurrentURL(){
    webdriver().shouldHave(url(profileUrl));
    LOGGER.info("URL correct");
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

    System.out.println(authCookieToken);
    System.out.println(authCookieExpires);
    System.out.println(authCookieUserID);
    LOGGER.info("Auth cookie filled");
  }

  @Step("Деавторизоваться")
  public void logOut() {
    profilePage.logOutBtn().click();
    LOGGER.info("Log out done");
  }

  @Step("Проверить имя пользователя")
  public void checkUserName(String name) {
    profilePage.userNameLabel().shouldHave(text(name));
    LOGGER.info("User Name correct");
  }

  @Step("Проверить имя пользователя")
  public void checkNotLoggingLabel() {
    profilePage.notLoggingLabel().shouldHave(text("Currently you are not logged into the Book Store "
        + "application, please visit the login page to enter or register page to register yourself."));
    LOGGER.info("Not logging label correct");
  }

  @Step("Ввести текст в поле поиска")
  public void fillSearchBookInput(String book) {
    profilePage.searchBookInput().sendKeys(book);
    LOGGER.info("Book for search filled");
  }

  @Step("Проверить текст пустой таблицы")
  public void checkTableLabel() {
    profilePage.emptyTableLabel();
    LOGGER.info("Table label empty");
  }
}
