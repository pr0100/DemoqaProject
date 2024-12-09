package api.steps;

import static api.utils.spec.Specification.authSpecification;
import static api.utils.spec.Specification.requestSpecification;
import static helpers.config.Endpoints.ACCOUNT_REG;
import static helpers.config.Endpoints.ACCOUNT_USER;
import static helpers.config.Endpoints.BOOK;
import static helpers.config.Endpoints.BOOK_ISBN;

import api.utils.templates.AddBookBody;
import api.utils.templates.DeleteBookBody;
import api.utils.templates.GetIsbnBody;
import api.utils.templates.UserAccountBody;
import api.utils.helpers.RestWrapper;
import helpers.auth.Authorization;
import helpers.config.TestData;
import io.qameta.allure.Step;

import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApiSteps {

  Authorization authorization = Authorization.getInstance();
  UserAccountBody userAccountBody = new UserAccountBody();
  AddBookBody addBookBody = new AddBookBody();
  DeleteBookBody deleteBookBody = new DeleteBookBody();
  GetIsbnBody getIsbnBody = new GetIsbnBody();
  HelpSteps helpSteps = new HelpSteps();
  protected static final Logger LOGGER = LogManager.getLogger();

  @Step("Получить код книги у пользователя")
  public String getIsbnBookFromAccount() {
    LOGGER.info("Got book from account");
    return getAccount(authorization.getUserId())
        .getBodyFieldStringList("books.isbn").get(0);
  }

  @Step("Получить информацию обо всех книгах")
  public RestWrapper getBooks() {
    return new RestWrapper()
        .get(requestSpecification(), new HashMap<>(), BOOK);
  }

  @Step("Получить информацию с аккаунта")
  public RestWrapper getAccount(String userId) {
    return new RestWrapper()
        .getWithPath(authSpecification(), new HashMap<>(),
            ACCOUNT_USER, userId);
  }

  @Step("Получить информацию о несущестующей книге")
  public RestWrapper getWrongBook() {
    HashMap<String, String> params = new HashMap<>();
    params.put("ISBN", TestData.getWrongIsbn());
    return new RestWrapper()
        .get(requestSpecification(), params, BOOK_ISBN);
  }

  @Step("Получить информацию о книге")
  public RestWrapper getBook() {
    HashMap<String, String> params = new HashMap<>();
    params.put("ISBN", helpSteps.getFirstIsbn());
    return new RestWrapper()
        .get(requestSpecification(), params, BOOK_ISBN);
  }

  @Step("Добавить книгу на аккаунт")
  public RestWrapper addBook(String isbn) {
    return new RestWrapper()
        .post(authSpecification(), BOOK,
            addBookBody.fillRegParamForAddBookRequest(authorization.getUserId(),
                getIsbnBody.getAvailableIsbn(isbn)));
  }

  @Step("Создать аккаунт")
  public RestWrapper addNewAccount() {
    return new RestWrapper()
        .post(authSpecification(), ACCOUNT_REG,
            userAccountBody.getParams(TestData.getSuccessfulUserName(), TestData.getSuccessfulPasswd()));
  }

  @Step("Удалить книгу с аккаунта")
  public RestWrapper deleteBook() {
    return new RestWrapper()
        .delete(authSpecification(), new HashMap<>(),
            deleteBookBody.fillRegParamForDeleteBookRequest(getIsbnBookFromAccount(), authorization.getUserId()),
            BOOK_ISBN);
  }

  @Step("Удалить книгу без авторизации")
  public RestWrapper deleteBookError() {
    return new RestWrapper()
        .delete(requestSpecification(), new HashMap<>(),
            "",
            BOOK_ISBN);
  }

  @Step("Удалить несущестующую книгу с аккаунта")
  public RestWrapper deleteWrongBook() {
    return new RestWrapper()
        .delete(authSpecification(), new HashMap<>(),
            deleteBookBody.fillRegParamForDeleteBookRequest(TestData.getWrongIsbn(), authorization.getUserId()),
            BOOK_ISBN);
  }

  @Step("Удалить все книги с аккаунта")
  public RestWrapper deleteAllBooks() {
    HashMap<String, String> params = new HashMap<>();
    params.put("UserId", authorization.getUserId());
    return new RestWrapper()
        .delete(authSpecification(), params, "", BOOK);
  }

}
