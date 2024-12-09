package helpers.auth;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static helpers.config.Endpoints.LOGIN;
import static helpers.config.Config.cfg;

import api.utils.templates.UserAccountBody;
import api.utils.helpers.RestWrapper;

import static api.utils.spec.Specification.requestSpecification;

import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Cookie;

public class Authorization {

  protected static final Logger LOGGER = LogManager.getLogger();
  private static final Authorization instance = new Authorization();
  private HashMap<String, String> authParams;
  private boolean isAuthenticated = false;

  private Authorization() {}

  UserAccountBody userAccountBody = new UserAccountBody();
  public static synchronized Authorization getInstance() {
    return instance;
  }

  private void authenticate() {
    if (!isAuthenticated) {
      LOGGER.info("Not Authenticated");
      RestWrapper postLogin = new RestWrapper()
          .post(requestSpecification(), LOGIN, userAccountBody.getParams(cfg.getUserName(), cfg.getPassword()));
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

  public void addCookie(String key, String value) {
    Cookie cookie =  new Cookie(key, value);
    getWebDriver().manage().addCookie(cookie);
  }

  public void loginCookie() {
    addCookie("userID", getUserId());
    addCookie("token", getToken());
    addCookie("expires", getTokenExpires());
  }
}
