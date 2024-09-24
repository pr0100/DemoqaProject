package helpers.config;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.baseURI;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {

  private static final WebConfig config = ConfigFactory.create(WebConfig.class);

  @BeforeEach
  void setUp() {
    Configuration.pageLoadStrategy = config.pageLoadStrategy();
    baseURI = config.baseUri();
    open(config.baseUrl());
    getWebDriver().manage().window().maximize();
  }

}
