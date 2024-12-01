package api.steps;

import static api.utils.spec.Specification.authSpecification;
import static api.utils.spec.Specification.requestSpecification;
import static helpers.config.Endpoints.ACCOUNT_REG;
import static helpers.config.Endpoints.ACCOUNT_USER;
import static helpers.config.Endpoints.BOOK;
import static helpers.config.Endpoints.BOOK_ISBN;

import api.utils.templates.AddBookBody;
import api.utils.templates.DeleteBookBody;
import api.utils.templates.UserAccountBody;
import api.utils.wrapper.RestWrapper;
import helpers.auth.Authorization;
import helpers.config.TestData;
import io.qameta.allure.Step;

import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApiSteps {

  Authorization authorization = Authorization.getInstance();
  AddBookBody addBookBody = new AddBookBody();
  DeleteBookBody deleteBookBody = new DeleteBookBody();
  protected static final Logger LOGGER = LogManager.getLogger();

  @Step("Получить isbn код")
  public String getFirstIsbn() {
    return getInfo(requestSpecification(), "", BOOK)
        .getBodyFieldString("books[0].isbn");
  }

  @Step("Задать путь для пользователя")
  public String setPathForUserID(){
    LOGGER.info("Path assigned for userID");
    return "?UserId=" + authorization.getUserId();
  }

  @Step("Получить инфо {path}")
  public RestWrapper getInfo(RequestSpecification spec, String param, String path) {
    return new RestWrapper()
        .get(spec, param, path);
  }

  @Step("Добавить книгу на аккаунт")
  public RestWrapper addBook() {
    return new RestWrapper()
        .post(authSpecification(), BOOK, addBookBody.fillRegParamForAddBookRequest());
  }

  @Step("Создать аккаунт")
  public RestWrapper addNewAccount() {
    return new RestWrapper()
        .post(authSpecification(), ACCOUNT_REG,
            UserAccountBody.getParams(TestData.getSuccessfulUserName(), TestData.getSuccessfulPasswd()));
  }

  @Step("Удалить книгу с аккаунта")
  public RestWrapper deleteBook(String isbn) {
    return new RestWrapper()
        .delete(authSpecification(),
            deleteBookBody.fillRegParamForDeleteBookRequest(isbn, authorization.getUserId()),
            BOOK_ISBN);
  }

  @Step("Удалить все книги с аккаунта")
  public RestWrapper deleteAllBooks() {
    return new RestWrapper()
        .delete(authSpecification(), "", BOOK + setPathForUserID());
  }

  @Step("Получить код книги у пользователя")
  public String getIsbnBookFromAccount() {
    LOGGER.info("Got book from account");
    return getInfo(authSpecification(), "",
        ACCOUNT_USER.replace("{UUID}", authorization.getUserId()))
        .getBodyFieldStringList("books.isbn").get(0);
  }

}
