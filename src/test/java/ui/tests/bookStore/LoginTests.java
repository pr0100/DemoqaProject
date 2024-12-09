package ui.tests.bookStore;


import static helpers.config.Config.cfg;
import static io.qameta.allure.Allure.step;


import helpers.config.TestData;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import ui.steps.bookStoreApp.LoginSteps;
import ui.steps.bookStoreApp.ProfileSteps;
import ui.steps.bookStoreApp.RegisterSteps;
import helpers.utils.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("ui")
@Feature("Тесты для вкладки Login")
@Tag("ui")
public class LoginTests extends BaseTest {

  LoginSteps loginSteps = new LoginSteps();
  ProfileSteps profileSteps = new ProfileSteps();
  RegisterSteps registerSteps = new RegisterSteps();

  @Test
  @Severity(SeverityLevel.CRITICAL)
  @DisplayName("Авторизация без данных")
  void loginWithoutData() {
    loginSteps.goToLoginPage();
    loginSteps.clickLoginBtnField();
    step("Проверки", () -> {
      loginSteps.checkUserNameBorderColor();
      loginSteps.checkPasswordBorderColor();
      loginSteps.checkCurrentURL();
    });
  }

  @Test
  @Severity(SeverityLevel.BLOCKER)
  @DisplayName("Успешная авторизация")
  void successfulLogin() {
    loginSteps.goToLoginPage();
    loginSteps.fillAuthDataField(cfg.getUserName(), cfg.getPassword());
    step("Проверки", () ->
      profileSteps.checkCurrentURL()
    );
  }

  @Test
  @Severity(SeverityLevel.CRITICAL)
  @DisplayName("Неуспешная авторизация")
  void unsuccessfulLogin() {
    loginSteps.goToLoginPage();
    loginSteps.fillAuthDataField(TestData.getSuccessfulUserName(), TestData.getWrongPassword());
    step("Проверки", () -> {
      loginSteps.checkCurrentURL();
      loginSteps.checkErrorMessage();
    });
  }

  @Test
  @Severity(SeverityLevel.BLOCKER)
  @DisplayName("Переход на страницу регистрации при нажатии кнопки New User")
  void newUserBtnToRegisterPage() {
    loginSteps.goToLoginPage();
    loginSteps.clickNewUserBtnField();
    step("Проверки", () ->
      registerSteps.checkCurrentURL()
    );
  }
}
