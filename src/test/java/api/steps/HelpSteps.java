package api.steps;

import static api.utils.spec.Specification.requestSpecification;
import static helpers.config.Endpoints.BOOK;

import api.utils.helpers.RestWrapper;
import helpers.config.TestData;
import io.qameta.allure.Step;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HelpSteps {

  protected static final Logger LOGGER = LogManager.getLogger();

  @Step("Вспомогательный шаг - Получить isbn код")
  public String getIsbn() {
    return new RestWrapper()
        .get(requestSpecification(), new HashMap<>(), BOOK)
        .getBodyFieldString("books[" + TestData.getElemArrayBooks() + "].isbn");
  }

  @Step("Вспомогательный шаг - Получить первый isbn код")
  public String getFirstIsbn() {
    return new RestWrapper()
        .get(requestSpecification(), new HashMap<>(), BOOK)
        .getBodyFieldString("books[0].isbn");
  }




}
