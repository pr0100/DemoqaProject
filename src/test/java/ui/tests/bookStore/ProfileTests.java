package ui.tests.bookStore;


import static helpers.config.Config.cfg;

import api.steps.ApiSteps;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import ui.steps.bookStoreApp.LoginSteps;
import ui.steps.bookStoreApp.ProfileSteps;
import helpers.utils.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("ui")
@Feature("Тесты для вкладки Profile")
@Tag("ui")
public class ProfileTests extends BaseTest {

  ProfileSteps profileSteps = new ProfileSteps();
  LoginSteps loginSteps = new LoginSteps();
  ApiSteps apiSteps = new ApiSteps();

  @Test
  @Severity(SeverityLevel.NORMAL)
  @DisplayName("Сообщение для неавторизованных пользователей")
  void profilePage() {
    profileSteps.goToProfilePage();
    profileSteps.checkNotLoggingLabel();
  }

  @Test
  @Severity(SeverityLevel.CRITICAL)
  @DisplayName("Деавторизация")
  void logout() {
    profileSteps.goToProfilePageAndLogin();
    profileSteps.logOut();
    loginSteps.checkCurrentURL();
  }

  @Test
  @Severity(SeverityLevel.NORMAL)
  @DisplayName("Имя пользователя")
  void userName() {
    profileSteps.goToProfilePageAndLogin();
    profileSteps.checkUserName(cfg.getUserName());
  }

  @Test
  @Severity(SeverityLevel.MINOR)
  @DisplayName("Поиск книги, которой нет у пользователя")
  void unsuccessfulSearchBookOnAccount() {
    profileSteps.goToProfilePageAndLogin();
    profileSteps.fillSearchBookInput("книга");
    profileSteps.checkTableLabel();
  }

  @Test
  @Severity(SeverityLevel.BLOCKER)
  @DisplayName("Удаление всех книг у пользователя")
  void deleteAllBooksFromAccount() {
    profileSteps.goToProfilePageAndLogin();
    profileSteps.deleteAllRecords();
    profileSteps.checkTableLabel();
  }

  @Test
  @Severity(SeverityLevel.CRITICAL)
  @DisplayName("Добавление книги API и удаление UI")
  void addApiDeleteUi() {
    apiSteps.addBook().shouldHaveStatusCode(201);
    profileSteps.goToProfilePageAndLogin();
    profileSteps.deleteRecord();
    profileSteps.checkTableLabel();
    profileSteps.logOut();
    loginSteps.checkCurrentURL();
  }

}
