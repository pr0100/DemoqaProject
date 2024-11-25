package api.utils.templates;

import api.steps.BookStoreApiSteps;
import api.utils.models.AddBookRequestModel;
import api.utils.models.DeleteBookModel;
import api.utils.models.UserAccountModel;
import helpers.auth.Authorization;

public class FillingModels {

  Authorization authorization = Authorization.getInstance();
  BookStoreApiSteps bookStoreApiSteps = new BookStoreApiSteps();

  public static UserAccountModel getParams(String userName, String password) {
    UserAccountModel regParams = new UserAccountModel();
    regParams.setUserName(userName);
    regParams.setPassword(password);
    return regParams;
  }

  public AddBookRequestModel fillRegParamForAddBookRequest() {
    AddBookRequestModel regParams = new AddBookRequestModel();
    regParams.setUserId(authorization.getUserId());
    regParams.setCollectionOfIsbns(bookStoreApiSteps.getAvailableIsbn());
    return regParams;
  }

  public DeleteBookModel fillRegParamForDeleteBookRequest(String isbn, String user) {
    DeleteBookModel regParams = new DeleteBookModel();
    regParams.setIsbn(isbn);
    regParams.setUserId(user);
    return regParams;
  }
}
