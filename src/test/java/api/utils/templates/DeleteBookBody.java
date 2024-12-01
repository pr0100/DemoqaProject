package api.utils.templates;

import api.utils.models.DeleteBookModel;

public class DeleteBookBody {

  public DeleteBookModel fillRegParamForDeleteBookRequest(String isbn, String user) {
    DeleteBookModel regParams = new DeleteBookModel();
    regParams.setIsbn(isbn);
    regParams.setUserId(user);
    return regParams;
  }

}
