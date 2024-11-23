package helpers.utils;

import static io.restassured.RestAssured.baseURI;
import static helpers.config.Config.cfg;

import helpers.auth.Authorization;
import org.junit.jupiter.api.BeforeAll;


public class ApiBase {

  @BeforeAll
  public static void setUp() {
    baseURI = cfg.baseUrl();
  }

}

