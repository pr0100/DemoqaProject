package helpers.config;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static helpers.config.Config.cfg;
import static io.restassured.RestAssured.baseURI;

import ui.steps.mainPage.MainPageSteps;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

  //private static final WebConfig config = ConfigFactory.create(WebConfig.class);

  MainPageSteps mainPageSteps = new MainPageSteps();

  @BeforeAll
  static void setUp() {
    Configuration.pageLoadStrategy = cfg.pageLoadStrategy();
    Configuration.screenshots = false;
    Configuration.savePageSource = false;
    baseURI = cfg.baseUrl();
    open(cfg.baseUrl());
    getWebDriver().manage().window().maximize();
  }

  @BeforeEach
  void openUrl() {
    mainPageSteps.goToMainPage();
  }

}
