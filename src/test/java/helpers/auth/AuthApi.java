package helpers.auth;

import static helpers.config.Endpoints.login;
import static helpers.config.Config.cfg;

import api.utils.wrapper.RestWrapper;
import api.utils.models.UserAccountModel;
import static api.utils.spec.Specification.requestSpecification;

import io.qameta.allure.Step;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuthApi {

  protected static final Logger LOGGER = LogManager.getLogger();

  private HashMap<String, String> authParams;
  private boolean isAuthenticated = false;

  public static UserAccountModel getParams(String userName, String password) {
    UserAccountModel regParams = new UserAccountModel();
    regParams.setUserName(userName);
    regParams.setPassword(password);
    return regParams;
  }

  @Step("Авторизация")
  private void authenticate() {
    if (!isAuthenticated) {
      LOGGER.info("Not Authenticated");
      RestWrapper postLogin = new RestWrapper()
              .post(requestSpecification, login, getParams(cfg.getUserName(), cfg.getPassword()));
      authParams = new HashMap<>();
      authParams.put("userId", postLogin.getBodyFieldString("userId"));
      authParams.put("token", postLogin.getBodyFieldString("token"));
      authParams.put("expires", postLogin.getBodyFieldString("expires"));
      isAuthenticated = true;
    }
    LOGGER.info("is Authenticated");
  }

  public String getUserId() {
    authenticate();
    LOGGER.info("Got userId");
    return authParams.get("userId");
  }

  public String getToken() {
    authenticate();
    LOGGER.info("Got Token");
    return authParams.get("token");
  }

  public String getTokenExpires() {
    authenticate();
    LOGGER.info("Got expires");
    return authParams.get("expires");
  }
}
