package api.steps;

import static api.utils.spec.Specification.requestSpecification;
import static helpers.config.Config.cfg;
import static helpers.config.Endpoints.book;


import api.utils.RestWrapper;
import helpers.auth.AuthApi;
import api.utils.models.AddBookRequestModel;
import api.utils.models.DeleteBookModel;
import api.utils.models.IsbnModel;
import helpers.config.TestData;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookStoreApiSteps {

  AuthApi authApi = new AuthApi();
  RestWrapper restWrapper = new RestWrapper();
  protected static final Logger LOGGER = LogManager.getLogger();


  public AddBookRequestModel fillRegParamForAddBookRequest() {
    AddBookRequestModel regParams = new AddBookRequestModel();
    regParams.setUserId(authApi.getUserId());
    regParams.setCollectionOfIsbns(getAvailableIsbn());
    return regParams;
  }

  public DeleteBookModel fillRegParamForDeleteBookRequest(String isbn, String user) {
    DeleteBookModel regParams = new DeleteBookModel();
    regParams.setIsbn(isbn);
    regParams.setUserId(user);
    return regParams;
  }

  @Step("Получить доступный isbn книги")
  private List<IsbnModel> getAvailableIsbn() {
    LOGGER.info("Got available isbn");
    List<IsbnModel> isbnList = new ArrayList<>();
    IsbnModel isbn = new IsbnModel();
    isbn.setIsbn(restWrapper.get(requestSpecification, book)
        .getBodyFieldString("books[" + TestData.getElemArrayBooks() + "].isbn"));
    isbnList.add(isbn);
    return isbnList;
  }

  @Step("Задать путь для поиска определенной книги")
  public String setPathForISBNSearch() {
    LOGGER.info("Path assigned for isbn search");
    return "?ISBN=" + cfg.getAvailableIsbn();
  }

  @Step("Задать путь для пользователя")
  public String setPathForUserID(){
    LOGGER.info("Path assigned for userID");
    return "?UserId=" + authApi.getUserId();
  }

  @Step("Задать путь для поиска несуществующей книги")
  public String setPathForWrongISBNSearch() {
    LOGGER.info("Path assigned for wrong isbn search");
    return "?ISBN=" + TestData.getWrongIsbn();
  }
}
