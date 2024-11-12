package api.steps;

import static helpers.config.Endpoints.accountUser;
import static api.utils.spec.Specification.authSpecification;


import api.utils.RestWrapper;
import helpers.auth.AuthApi;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;

public class AccountApiSteps {

  AuthApi authApi = new AuthApi();
  protected static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

  @Step("Получить код книги у пользователя")
  public String getIsbnBookFromAccount() {
    try {
      LOGGER.info("Got book from account");
      return new RestWrapper()
              .get(authSpecification, accountUser + authApi.getUserId())
              .getBodyFieldStringList("books.isbn").get(0);
    } catch (Exception e) {
      LOGGER.error("Receive error book from account");
      return "Error";
    }
  }

}
