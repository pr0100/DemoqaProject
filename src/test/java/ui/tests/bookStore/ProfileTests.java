package ui.tests.bookStore;


import static api.utils.spec.Specification.authSpecification;
import static helpers.config.Config.cfg;
import static helpers.config.Endpoints.BOOK;

import api.utils.templates.FillingModels;
import api.utils.wrapper.RestWrapper;
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
  FillingModels fillingModels = new FillingModels();

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
    profileSteps.goToProfilePage();
    profileSteps.addAuthCookie();
    profileSteps.logOut();
    loginSteps.checkCurrentURL();
  }

  @Test
  @Severity(SeverityLevel.NORMAL)
  @DisplayName("Имя пользователя")
  void userName() {
    profileSteps.goToProfilePage();
    profileSteps.addAuthCookie();
    profileSteps.checkUserName(cfg.getUserName());
  }

  @Test
  @Severity(SeverityLevel.MINOR)
  @DisplayName("Поиск книги, которой нет у пользователя")
  void unsuccessfulSearchBookOnAccount() {
    profileSteps.goToProfilePage();
    profileSteps.addAuthCookie();
    profileSteps.fillSearchBookInput("книга");
    profileSteps.checkTableLabel();
  }

  @Test
  @Severity(SeverityLevel.BLOCKER)
  @DisplayName("Удаление всех книг у пользователя")
  void deleteAllBooksFromAccount() {
    profileSteps.goToProfilePage();
    profileSteps.addAuthCookie();
    profileSteps.deleteAllRecords();
    profileSteps.checkTableLabel();
  }

  @Test
  @Severity(SeverityLevel.CRITICAL)
  @DisplayName("Добавление книги API и удаление UI")
  void addAPIDeleteUI() {
    new RestWrapper()
        .post(authSpecification(),
            BOOK,
            fillingModels.fillRegParamForAddBookRequest())
        .shouldHaveStatusCode(201);

    profileSteps.goToProfilePage();
    profileSteps.addAuthCookie();
    profileSteps.deleteRecord();
    profileSteps.checkTableLabel();
    profileSteps.logOut();
    loginSteps.checkCurrentURL();
  }

}
