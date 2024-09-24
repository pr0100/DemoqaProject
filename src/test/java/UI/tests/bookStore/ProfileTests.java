package UI.tests.bookStore;

import static helpers.config.TestData.defaultUserName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

import UI.steps.bookStoreApp.LoginSteps;
import UI.steps.bookStoreApp.ProfileSteps;
import helpers.config.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тесты для вкладки Profile")
public class ProfileTests extends TestBase {

  ProfileSteps profileSteps = new ProfileSteps();
  LoginSteps loginSteps = new LoginSteps();

  @Test
  @DisplayName("Сообщение для неавторизованных пользователей")
  void profilePage() {
    profileSteps.goToProfilePage();
    profileSteps.checkNotLoggingLabel();
  }

  @Test
  @DisplayName("Деавторизация")
  void logout() {
    profileSteps.goToProfilePage();
    profileSteps.addAuthCookie();
    profileSteps.logOut();
    loginSteps.checkCurrentURL();
  }

  @Test
  @DisplayName("Имя пользователя")
  void userName() {
    profileSteps.goToProfilePage();
    profileSteps.addAuthCookie();
    profileSteps.checkUserName(defaultUserName);
  }

  @Test
  @DisplayName("Поиск книги, которой нет у пользователя")
  void unsuccessfulSearchBookOnAccount() {
    profileSteps.goToProfilePage();
    profileSteps.addAuthCookie();
    profileSteps.fillSearchBookInput("книга");
    profileSteps.checkTableLabel();
  }

}
