package api.utils.templates;

import api.utils.models.AddBookRequestModel;
import helpers.auth.Authorization;

public class AddBookBody {

  GetIsbnBody getIsbnBody = new GetIsbnBody();

  public AddBookRequestModel fillRegParamForAddBookRequest() {
    AddBookRequestModel regParams = new AddBookRequestModel();
    regParams.setUserId(Authorization.getInstance().getUserId());
    regParams.setCollectionOfIsbns(getIsbnBody.getAvailableIsbn());
    return regParams;
  }

}
