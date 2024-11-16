package ui.tests.bookStore;


import static helpers.config.Config.cfg;

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
@DisplayName("Тесты для вкладки Profile")
@Tag("ui")
public class ProfileTests extends BaseTest {

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
    profileSteps.checkUserName(cfg.getUserName());
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
