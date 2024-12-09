package ui.tests.bookStore;


import static helpers.config.Config.cfg;
import static io.qameta.allure.Allure.step;

import api.steps.ApiSteps;
import api.steps.HelpSteps;
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
  HelpSteps helpSteps = new HelpSteps();

  @Test
  @Severity(SeverityLevel.NORMAL)
  @DisplayName("Сообщение для неавторизованных пользователей")
  void profilePage() {
    profileSteps.goToProfilePage();
    step("Проверки", () ->
      profileSteps.checkNotLoggingLabel()
    );
  }

  @Test
  @Severity(SeverityLevel.CRITICAL)
  @DisplayName("Деавторизация")
  void logout() {
    step("Предварительные шаги", () ->
      profileSteps.goToProfilePageAndLogin()
    );
    profileSteps.logOut();
    step("Проверки", () ->
      loginSteps.checkCurrentURL()
    );
  }

  @Test
  @Severity(SeverityLevel.NORMAL)
  @DisplayName("Имя пользователя")
  void userName() {
    profileSteps.goToProfilePageAndLogin();
    step("Проверки", () ->
      profileSteps.checkUserName(cfg.getUserName())
    );
  }

  @Test
  @Severity(SeverityLevel.MINOR)
  @DisplayName("Поиск книги, которой нет у пользователя")
  void unsuccessfulSearchBookOnAccount() {
    step("Предварительные шаги", () ->
      profileSteps.goToProfilePageAndLogin()
    );
    profileSteps.fillSearchBookInput("книга");
    step("Проверки", () ->
      profileSteps.checkTableLabel()
    );
  }

  @Test
  @Severity(SeverityLevel.BLOCKER)
  @DisplayName("Удаление всех книг у пользователя")
  void deleteAllBooksFromAccount() {
    step("Предварительные шаги", () ->
      profileSteps.goToProfilePageAndLogin()
    );
    profileSteps.deleteAllRecords();
    step("Проверки", () ->
      profileSteps.checkTableLabel()
    );
  }

  @Test
  @Severity(SeverityLevel.CRITICAL)
  @DisplayName("Удаление одной книги")
  void addApiDeleteUi() {
    step("Предварительные шаги", () -> {
      String isbn = helpSteps.getIsbn();
      apiSteps.addBook(isbn).shouldHaveStatusCode(201);
      profileSteps.goToProfilePageAndLogin();
    });
    profileSteps.deleteRecord();
    step("Проверки", () -> {
      profileSteps.checkTableLabel();
      profileSteps.logOut();
      loginSteps.checkCurrentURL();
    });
  }

}
