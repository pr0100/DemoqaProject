package api.steps;

import static helpers.config.Endpoints.accountUser;
import static api.utils.spec.Specification.requestSpecification;


import api.utils.RestWrapper;
import helpers.auth.AuthApi;
import io.qameta.allure.Step;

public class AccountApiSteps {

  AuthApi authApi = new AuthApi();

  @Step("Получить код книги у пользователя")
  public String getIsbnBookFromAccount() {
    try {
      return new RestWrapper()
              .getAuth(requestSpecification, accountUser, authApi.getUserId())
              .getBodyFieldStringList("books.isbn").get(0);
    } catch (Exception e) {
      return "Error";
    }
  }

}
