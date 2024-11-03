package UI.tests.bookStore;


import static helpers.config.Config.cfg;

import static helpers.config.TestData.userName;
import static helpers.config.TestData.wrongPassword;

import UI.steps.bookStoreApp.LoginSteps;
import UI.steps.bookStoreApp.ProfileSteps;
import UI.steps.bookStoreApp.RegisterSteps;
import helpers.config.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("UI")
@Feature("Тесты для вкладки Login")
@DisplayName("Тесты для вкладки Login")
@Tag("demoqaUI")
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
    loginSteps.fillAuthDataField(userName, wrongPassword);
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
