package ui.steps.bookStoreApp;

import static helpers.config.Endpoints.registrUrl;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

import io.qameta.allure.Step;

public class RegisterSteps {

  @Step("Проверить текущий URL")
  public void checkCurrentURL(){
    webdriver().shouldHave(url(registrUrl));
  }

}
