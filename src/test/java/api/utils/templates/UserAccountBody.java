package api.utils.templates;

import api.utils.models.UserAccountModel;

public class UserAccountBody {

  public static UserAccountModel getParams(String userName, String password) {
    UserAccountModel regParams = new UserAccountModel();
    regParams.setUserName(userName);
    regParams.setPassword(password);
    return regParams;
  }
}
