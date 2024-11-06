package api.steps;

import static api.utils.spec.Specification.requestSpecification;
import static helpers.config.Config.cfg;
import static helpers.config.Endpoints.book;
import static helpers.config.TestData.wrongIsbn;


import api.utils.RestWrapper;
import helpers.auth.AuthApi;
import api.utils.models.AddBookRequestModel;
import api.utils.models.DeleteBookModel;
import api.utils.models.IsbnModel;
import helpers.config.TestData;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;

public class BookStoreApiSteps {

  AuthApi authApi = new AuthApi();
  RestWrapper restWrapper = new RestWrapper();

  @Step
  private List<IsbnModel> getRandomListAvailableIsbn() {
      List<IsbnModel> isbnList = new ArrayList<>();
      IsbnModel isbn = new IsbnModel();
      isbn.setIsbn(restWrapper.getNotAuth(requestSpecification, book, "")
              .getBodyFieldString("books[" + TestData.getElemArrayBooks() + "].isbn"));
      isbnList.add(isbn);
      return isbnList;
  }

  @Step
  private String isbn(int number) {
    return new RestWrapper()
            .getNotAuth(requestSpecification, book, "")
            .getBodyFieldString("books[" + number + "].isbn");
  }

  @Step("Получить все коды книг")
  public String getAllIsbn() {
    return new RestWrapper()
            .getNotAuth(requestSpecification, book, "")
            .getBodyFieldString("books.isbn");
  }


  private int getLength() {
    return restWrapper.getNotAuth(requestSpecification, book, "")
            .shouldHaveStatusCode(200)
            .getBodyFieldStringList("books").size();
  }

  public AddBookRequestModel fillRegParamForAddBookRequest() {
    AddBookRequestModel regParams = new AddBookRequestModel();
    regParams.setUserId(authApi.getUserId());
    regParams.setCollectionOfIsbns(getRandomListAvailableIsbn());
    return regParams;
  }

  public DeleteBookModel fillRegParamForDeleteBookRequest(String isbn, String user) {
    DeleteBookModel regParams = new DeleteBookModel();
    regParams.setIsbn(isbn);
    regParams.setUserId(user);
    return regParams;
  }

  @Step("Путь для поиска определенной книги")
  public String setPathForISBNSearch() {
    return "?ISBN=" + cfg.getAvailableIsbn();
  }

  @Step("Путь для пользователя")
  public String setPathForUserID(){
    return "?UserId=" + authApi.getUserId();
  }

  @Step("Путь для поиска несуществующей книги")
  public String setPathForWrongISBNSearch() {
    return "?ISBN=" + wrongIsbn;
  }
}
