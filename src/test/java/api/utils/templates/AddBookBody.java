package api.utils.templates;

import api.utils.models.AddBookRequestModel;
import api.utils.models.IsbnModel;
import java.util.List;

public class AddBookBody {

  public AddBookRequestModel fillRegParamForAddBookRequest(String userId, List<IsbnModel> isbn) {
    AddBookRequestModel regParams = new AddBookRequestModel();
    regParams.setUserId(userId);
    regParams.setCollectionOfIsbns(isbn);
    return regParams;
  }

}
