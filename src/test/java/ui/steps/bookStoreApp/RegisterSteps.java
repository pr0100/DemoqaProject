package ui.steps.bookStoreApp;

import static helpers.config.Endpoints.REGISTER_URL;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegisterSteps {

  protected final Logger LOGGER = LogManager.getLogger();

  @Step("Проверить текущий URL")
  public void checkCurrentURL(){
    webdriver().shouldHave(url(REGISTER_URL));
    LOGGER.info("URL correct");
  }

}
