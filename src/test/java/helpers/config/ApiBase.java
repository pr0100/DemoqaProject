package helpers.config;

import static io.restassured.RestAssured.baseURI;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;

public class ApiBase {

  private static final WebConfig config = ConfigFactory.create(WebConfig.class);

  @BeforeAll
  public static void setUp()
  {
    baseURI = config.baseUri();
  }
}
