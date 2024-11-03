package api.steps;

import static api.utils.spec.Specification.requestSpecification;
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

  private List<IsbnModel> getRandomListAvailableIsbn() {
      List<IsbnModel> isbnList = new ArrayList<>();
      IsbnModel isbn = new IsbnModel();
      isbn.setIsbn(restWrapper.getNotAuth(requestSpecification, book, "")
              .getBodyFieldString("books[" + TestData.getElemArrayBooks() + "].isbn"));
      isbnList.add(isbn);
//      for (int i = 0; i < number; i++){
//        IsbnModel isbn = new IsbnModel();
//        isbn.setIsbn(restWrapper.getNotAuth(requestSpecification, book, "")
//                        .getBodyFieldString("books[" + getElemArrayBooks() + "].isbn"));
//        isbnList.add(isbn);
//      }
      return isbnList;
  }



  private String isbn(int number) {
    return new RestWrapper()
            .getNotAuth(requestSpecification, book, "")
            .getBodyFieldString("books[" + number + "].isbn");
  }

//  private String isbn() {
//    return new RestWrapper()
//            .getNotAuth(requestSpecification, book, "")
//            .getBodyFieldString("books.isbn");
//  }

  @Step("Получить все коды книг")
  public String getAllIsbn() {
    return new RestWrapper()
            .getNotAuth(requestSpecification, book, "")
            .getBodyFieldString("books.isbn");
//    List<String> isbnList = new ArrayList<>();
//    for (int i = 0; i < getLength(); i++){
//      isbnList.add(isbn(i));
//    }
//    return isbnList;
  }

  private int getLength() {
    return restWrapper.getNotAuth(requestSpecification, book, "")
            .shouldHaveStatusCode(200)
            .getBodyFieldStringList("books").size();
  }

  @Step("Заполнить параметры запроса для добавления книги")
  public AddBookRequestModel fillRegParamForAddBookRequest() {
    AddBookRequestModel regParams = new AddBookRequestModel();
    regParams.setUserId(authApi.getUserId());
    regParams.setCollectionOfIsbns(getRandomListAvailableIsbn());
    return regParams;
  }

  @Step("Заполнить параметры запроса для удаления книги")
  public DeleteBookModel fillRegParamForDeleteBookRequest(String isbn, String user) {
    DeleteBookModel regParams = new DeleteBookModel();
    regParams.setIsbn(isbn);
    regParams.setUserId(user);
    return regParams;
  }

  @Step("Путь для поиска определенной книги")
  public String setPathForISBNSearch() {
    return "?ISBN=" + isbn(TestData.getElemArrayBooks());
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
