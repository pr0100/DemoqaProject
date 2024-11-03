package helpers.config;

import static io.restassured.RestAssured.baseURI;
import static helpers.config.Config.cfg;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;

public class ApiBase {

  //private static final WebConfig config = ConfigFactory.create(WebConfig.class);

  @BeforeAll
  public static void setUp()
  {
    baseURI = cfg.baseUrl();
  }
}
