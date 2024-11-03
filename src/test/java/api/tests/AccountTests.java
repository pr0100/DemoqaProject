package api.tests;

import api.utils.RestWrapper;
import helpers.auth.AuthApi;
import helpers.config.ApiBase;
import helpers.config.TestData;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import static api.utils.spec.Specification.requestSpecification;
import static helpers.config.Endpoints.accountUser;
import static helpers.config.Endpoints.book;
import static helpers.config.TestData.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.emptyOrNullString;
import static helpers.config.Config.cfg;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("API")
@Feature("Тесты методов Account")
@DisplayName("Тесты методов Account")
@Tag("demoqaAPI")
public class AccountTests extends ApiBase {

  AuthApi authApi = new AuthApi();

  @Test
  @DisplayName("Информация об аккаунте")
  void getAccountData() {
    new RestWrapper()
            .getAuth(requestSpecification, accountUser, authApi.getUserId())
            .shouldHaveStatusCode(200)
            .shouldHaveJsonPath("username", containsString(cfg.getUserName()));
  }

  @Test
  @DisplayName("Успешная регистрация пользователя")
  void successfulRegistrationUser() {
    RestWrapper answer = new RestWrapper()
            .post(requestSpecification, accountUser, authApi.getParams(TestData.getSuccessfulUserName(), TestData.getSuccessfulPasswd()));
    answer.shouldHaveStatusCode(201)
            .shouldHaveJsonPath("userID", not(emptyOrNullString()));
    assertTrue(answer.getBodyFieldStringList("books").isEmpty());
  }

  @Test
  void test() {
    System.out.println(
            new RestWrapper()
            .getNotAuth(requestSpecification, book, "")
            .getBodyFieldString("books.isbn"));
  }

}
