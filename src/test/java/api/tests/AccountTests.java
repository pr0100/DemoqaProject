package api.tests;

import api.steps.ApiSteps;
import helpers.auth.Authorization;
import helpers.utils.ApiBase;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static api.utils.helpers.CustomMatchers.emptyOrNullOrEmptyArray;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.emptyOrNullString;
import static helpers.config.Config.cfg;


@Epic("api")
@Feature("Тесты методов Account")
@Tag("api")
public class AccountTests extends ApiBase {

  ApiSteps apiSteps = new ApiSteps();
  String userId = Authorization.getInstance().getUserId();


  @Test
  @Severity(SeverityLevel.CRITICAL)
  @DisplayName("Информация об аккаунте")
  void getAccountData() {
    apiSteps.getAccount(userId)
            .shouldHaveStatusCode(200)
            .shouldHaveJsonPath("username", containsString(cfg.getUserName()));
  }

  @Test
  @Severity(SeverityLevel.BLOCKER)
  @DisplayName("Успешная регистрация пользователя")
  void successfulRegistrationUser() {
    apiSteps.addNewAccount()
        .shouldHaveStatusCode(201)
        .shouldHaveJsonPath("userID", not(emptyOrNullString()))
        .shouldHaveJsonPath("books", emptyOrNullOrEmptyArray());
  }
}
