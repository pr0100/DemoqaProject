package ui.steps.bookStoreApp;

import static helpers.config.Endpoints.PROFILE_URL;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

import helpers.auth.Authorization;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ui.pages.bookStoreApp.ProfilePage;
import ui.steps.mainPage.MainPageSteps;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

public class ProfileSteps {

  MainPageSteps mainPageSteps = new MainPageSteps();
  BookStoreSteps bookStoreSteps = new BookStoreSteps();
  ProfilePage profilePage = new ProfilePage();
  Authorization authorization = Authorization.getInstance();
  protected static final Logger LOGGER = LogManager.getLogger();

  @Step("Перейти на страницу Profile")
  public void goToProfilePage() {
    Selenide.clearBrowserCookies();
    mainPageSteps.goToBookStorePage();
    bookStoreSteps.goToProfilePage();
    LOGGER.info("Profile page opened");
  }

  @Step("Перейти на страницу Profile и авторизоваться")
  public void goToProfilePageAndLogin() {
    goToProfilePage();
    addAuthCookie();
    LOGGER.info("Login success");
  }

  @Step("Проверить текущий URL")
  public void checkCurrentURL(){
    webdriver().shouldHave(url(PROFILE_URL));
    LOGGER.info("URL correct");
  }

  @Step("Ввести куки авторизации")
  public void addAuthCookie(){
    authorization.loginCookie();
    getWebDriver().navigate().refresh();
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
    profilePage.searchBookInput().setValue(book);
    LOGGER.info("Book for search filled");
  }

  @Step("Проверить текст пустой таблицы")
  public void checkTableLabel() {
    profilePage.emptyTableLabel();
    LOGGER.info("Table label empty");
  }

  @Step("Удалить книгу")
  public void deleteRecord(){
    profilePage.deleteRecordBtn().click();
    profilePage.modalFormOkBtn().click();
    LOGGER.info("Record deleted");
  }

  @Step("Удалить все книги")
  public void deleteAllRecords() {
    profilePage.deleteAllRecordsBtn().click();
    profilePage.modalFormOkBtn().click();
    LOGGER.info("All records deleted");
  }
}
