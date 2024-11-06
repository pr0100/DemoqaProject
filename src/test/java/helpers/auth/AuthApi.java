package helpers.auth;

import static helpers.config.Endpoints.login;
import static helpers.config.Config.cfg;
import static io.restassured.RestAssured.given;

import api.utils.RestWrapper;
import api.utils.models.UserAccountModel;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

public class AuthApi {

  private HashMap<String, String> authParams;
  private boolean isAuthenticated = false;

  public UserAccountModel getParams(String userName, String password) {
    UserAccountModel regParams = new UserAccountModel();
    regParams.setUserName(userName);
    regParams.setPassword(password);
    return regParams;
  }

  private void authenticate() {
    if (!isAuthenticated) {
      RestWrapper postLogin = new RestWrapper()
              .postWithoutSpec(login, getParams(cfg.getUserName(), cfg.getPassword()));
      authParams = new HashMap<>();
      authParams.put("userId", postLogin.getBodyFieldString("userId"));
      authParams.put("token", postLogin.getBodyFieldString("token"));
      authParams.put("expires", postLogin.getBodyFieldString("expires"));
      isAuthenticated = true;
    }
  }

  public String getUserId() {
    authenticate();
    return authParams.get("userId");
  }

  public String getToken() {
    authenticate();
    return authParams.get("token");
  }

  public String getTokenExpires() {
    authenticate();
    return authParams.get("expires");
  }

  public RequestSpecification prepareAuthRequest(RequestSpecification spec, String endpoint) {
    return given(spec)
        .auth().oauth2(getToken())
        .basePath(endpoint);
  }

  public RequestSpecification prepareBearerAuthRequest(RequestSpecification spec, String endpoint) {
    return given(spec)
        .header("Authorization", "Bearer " + getToken())
        .basePath(endpoint);
  }

  public RequestSpecification prepareNotAuthRequest(RequestSpecification spec, String endpoint) {
    return given(spec)
        .basePath(endpoint);
  }
}
