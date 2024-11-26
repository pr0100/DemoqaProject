package api.steps;

import static api.utils.spec.Specification.requestSpecification;
import static helpers.config.Config.cfg;


import api.utils.wrapper.RestWrapper;
import helpers.auth.Authorization;
import api.utils.models.IsbnModel;
import helpers.config.Endpoints;
import helpers.config.TestData;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookStoreApiSteps {

  Authorization authorization = Authorization.getInstance();
  RestWrapper restWrapper = new RestWrapper();
  protected final Logger LOGGER = LogManager.getLogger();

  @Step("Получить доступный isbn книги")
  public List<IsbnModel> getAvailableIsbn() {
    LOGGER.info("Got available isbn");
    List<IsbnModel> isbnList = new ArrayList<>();
    IsbnModel isbn = new IsbnModel();
    isbn.setIsbn(restWrapper.get(requestSpecification(), Endpoints.BOOK)
        .getBodyFieldString("books[" + TestData.getElemArrayBooks() + "].isbn"));
    isbnList.add(isbn);
    return isbnList;
  }

  @Step
  public String getFirstIsbn() {
    return restWrapper.get(requestSpecification(), Endpoints.BOOK)
        .getBodyFieldString("books[0].isbn");
  }

  @Step("Задать путь для поиска определенной книги")
  public String setPathForISBNSearch() {
    LOGGER.info("Path assigned for isbn search");
    return "?ISBN=" + getFirstIsbn();
  }

  @Step("Задать путь для пользователя")
  public String setPathForUserID(){
    LOGGER.info("Path assigned for userID");
    return "?UserId=" + authorization.getUserId();
  }

  @Step("Задать путь для поиска несуществующей книги")
  public String setPathForWrongISBNSearch() {
    LOGGER.info("Path assigned for wrong isbn search");
    return "?ISBN=" + TestData.getWrongIsbn();
  }
}
