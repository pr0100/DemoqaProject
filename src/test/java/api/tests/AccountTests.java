package api.tests;

import api.utils.wrapper.RestWrapper;
import helpers.auth.AuthApi;
import helpers.utils.ApiBase;
import helpers.config.TestData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import static api.utils.spec.Specification.authSpecification;
import static helpers.config.Endpoints.accountUser;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.emptyOrNullString;
import static helpers.config.Config.cfg;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("api")
@Feature("Тесты методов Account")
@DisplayName("Тесты методов Account")
@Tag("api")
public class AccountTests extends ApiBase {

  AuthApi authApi = new AuthApi();

  @Test
  @DisplayName("Информация об аккаунте")
  void getAccountData() {
    new RestWrapper()
            .get(authSpecification,accountUser + authApi.getUserId())
            .shouldHaveStatusCode(200)
            .shouldHaveJsonPath("username", containsString(cfg.getUserName()));
  }

  @Test
  @DisplayName("Успешная регистрация пользователя")
  void successfulRegistrationUser() {
    RestWrapper answer = new RestWrapper()
            .post(authSpecification,
                accountUser, authApi.getParams(TestData.getSuccessfulUserName(), TestData.getSuccessfulPasswd()));
    answer.shouldHaveStatusCode(201)
            .shouldHaveJsonPath("userID", not(emptyOrNullString()));
    assertTrue(answer.getBodyFieldStringList("books").isEmpty());
  }


}
