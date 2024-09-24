package API.steps;

import static helpers.config.Endpoints.accountUser;
import static API.utils.spec.AccountSpec.getResponseSpec;
import static API.utils.spec.AccountSpec.postResponseSpec;
import static API.utils.spec.AccountSpec.registrRequestSpec;
import static helpers.config.TestData.defaultUserName;
import static helpers.config.TestData.password;
import static helpers.config.TestData.userName;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import helpers.auth.AuthApi;
import API.utils.models.UserAccountResponseModel;
import API.utils.models.UserResponseModel;
import io.qameta.allure.Step;

public class AccountApiSteps {

  AuthApi authApi = new AuthApi();

  private UserResponseModel getAllAccountData(String userID) {
    return authApi.prepareBearerAuthRequest(registrRequestSpec, accountUser)
        .when()
        .get(userID)
        .then()
        .spec(getResponseSpec)
        .extract().as(UserResponseModel.class);
  }

  public String getIsbnBookFromAccount(String userID) {
    UserResponseModel response = getAllAccountData(userID);
    if (response.getBooks().isEmpty()) {
      return "";
    }
    return response.getBooks().get(0).getIsbn();
  }

  private UserAccountResponseModel createNewUser() {
    return
        authApi.prepareNotAuthRequest(registrRequestSpec, accountUser)
        .body(authApi.getParams(userName, password))
        .when()
        .post()
        .then()
        .spec(postResponseSpec)
        .extract().as(UserAccountResponseModel.class);
  }

  @Step
  public void registrUser() {
    UserAccountResponseModel response = createNewUser();
    assertEquals(authApi.getParams(userName, password).getUserName(), response.getUsername());
    assertNotNull(response.getUserID());
    assertTrue(response.getBooks().isEmpty());
  }


  @Step
  public void accountData(String userID) {
    UserResponseModel response = getAllAccountData(userID);
    assertEquals(defaultUserName, response.getUsername());
  }

}
