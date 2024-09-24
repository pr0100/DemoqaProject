package API.tests;

import static helpers.config.TestData.defaultUserID;

import API.steps.AccountApiSteps;
import helpers.config.ApiBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тесты методов Account")
public class AccountTests extends ApiBase {

  AccountApiSteps accountApiSteps = new AccountApiSteps();

  @Test
  @DisplayName("Информация об аккаунте")
  void getAccountData() {
    accountApiSteps.accountData(defaultUserID);
  }

  @Test
  @DisplayName("Успешная регистрация пользователя")
  void successfulRegistrationUser() {
    accountApiSteps.registrUser();
  }

}
