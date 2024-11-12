package ui.tests.bookStore;


import static helpers.config.Config.cfg;


import helpers.config.TestData;
import ui.steps.bookStoreApp.LoginSteps;
import ui.steps.bookStoreApp.ProfileSteps;
import ui.steps.bookStoreApp.RegisterSteps;
import helpers.config.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("ui")
@Feature("Тесты для вкладки Login")
@DisplayName("Тесты для вкладки Login")
@Tag("ui")
public class LoginTests extends BaseTest {

  LoginSteps loginSteps = new LoginSteps();
  ProfileSteps profileSteps = new ProfileSteps();
  RegisterSteps registerSteps = new RegisterSteps();

  @Test
  @DisplayName("Авторизация без данных")
  void loginWithoutData() {
    loginSteps.goToLoginPage();
    loginSteps.clickLoginBtnField();
    loginSteps.checkUserNameBorderColor();
    loginSteps.checkPasswordBorderColor();
    loginSteps.checkCurrentURL();
  }

  @Test
  @DisplayName("Успешная авторизация")
  void successfulLogin() {
    loginSteps.goToLoginPage();
    loginSteps.fillAuthDataField(cfg.getUserName(), cfg.getPassword());
    profileSteps.checkCurrentURL();
  }

  @Test
  @DisplayName("Неуспешная авторизация")
  void unsuccessfulLogin() {
    loginSteps.goToLoginPage();
    loginSteps.fillAuthDataField(TestData.getSuccessfulUserName(), TestData.getWrongPassword());
    loginSteps.checkCurrentURL();
    loginSteps.checkErrorMessage();
  }

  @Test
  @DisplayName("Переход на страницу регистрации при нажатии кнопки New User")
  void newUserBtnToRegisterPage() {
    loginSteps.goToLoginPage();
    loginSteps.clickNewUserBtnField();
    registerSteps.checkCurrentURL();
  }
}
