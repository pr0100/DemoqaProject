package api.tests;

import api.utils.templates.FillingModels;
import api.utils.wrapper.RestWrapper;
import helpers.auth.Authorization;
import helpers.utils.ApiBase;
import helpers.config.TestData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import static api.utils.spec.Specification.authSpecification;
import static helpers.config.Endpoints.ACCOUNT_USER;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.emptyOrNullString;
import static helpers.config.Config.cfg;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("api")
@Feature("Тесты методов Account")
@Tag("api")
public class AccountTests extends ApiBase {

  Authorization authorization = Authorization.getInstance();

  @Test
  @Severity(SeverityLevel.CRITICAL)
  @DisplayName("Информация об аккаунте")
  void getAccountData() {
    new RestWrapper()
            .get(authSpecification(), ACCOUNT_USER + authorization.getUserId())
            .shouldHaveStatusCode(200)
            .shouldHaveJsonPath("username", containsString(cfg.getUserName()));
  }

  @Test
  @Severity(SeverityLevel.BLOCKER)
  @DisplayName("Успешная регистрация пользователя")
  void successfulRegistrationUser() {
    RestWrapper answer = new RestWrapper()
            .post(authSpecification(),
                ACCOUNT_USER, FillingModels.getParams(TestData.getSuccessfulUserName(), TestData.getSuccessfulPasswd()));
    answer.shouldHaveStatusCode(201)
            .shouldHaveJsonPath("userID", not(emptyOrNullString()));
    assertTrue(answer.getBodyFieldStringList("books").isEmpty());
  }


}
