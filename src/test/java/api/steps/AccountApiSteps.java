package api.steps;

import static helpers.config.Endpoints.ACCOUNT_USER;
import static api.utils.spec.Specification.authSpecification;


import api.utils.wrapper.RestWrapper;
import helpers.auth.Authorization;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;

public class AccountApiSteps {

  Authorization authorization = new Authorization();
  protected final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

  @Step("Получить код книги у пользователя")
  public String getIsbnBookFromAccount() {
    try {
      LOGGER.info("Got book from account");
      return new RestWrapper()
              .get(authSpecification, ACCOUNT_USER + authorization.getUserId())
              .getBodyFieldStringList("books.isbn").get(0);
    } catch (Exception e) {
      LOGGER.error("Receive error book from account");
      return "Error";
    }
  }

}
