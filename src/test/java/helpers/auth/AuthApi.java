package helpers.auth;

import static helpers.config.Endpoints.login;
import static helpers.config.Endpoints.token;
import static helpers.config.TestData.defaultPassword;
import static helpers.config.TestData.defaultUserName;
import static io.restassured.RestAssured.given;

import API.utils.models.UserAccountModel;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AuthApi {

  public UserAccountModel getParams(String userName, String password) {
    UserAccountModel regParams = new UserAccountModel();
    regParams.setUserName(userName);
    regParams.setPassword(password);
    return regParams;
  }

  public Response getLoginResponse() {
    return given()
        .contentType("application/json")
        .body(getParams(defaultUserName, defaultPassword))
        .when()
        .post(login)
        .then()
        .extract()
        .response();
  }

  public Response getTokenResponse() {
    return given()
        .contentType("application/json")
        .body(getParams(defaultUserName, defaultPassword))
        .when()
        .post(token)
        .then()
        .extract()
        .response();
  }

//  public AuthCred basicAuth() {
//    Response responseUser = getLoginResponse();
//    String username = responseUser.path("username");
//    String password = responseUser.path("password");
//    String userID = responseUser.path("userId");
//    return new AuthCred(username, password, userID);
//  }

//  public String userID() {
//    Response responseUserID = getLoginResponse();
//    return responseUserID.path("userId");
//  }
//
//  public String token() {
//    Response responseToken = getTokenResponse();
//    return responseToken.path("token");
//  }
//
//  public String tokenExpires() {
//    Response responseToken = getTokenResponse();
//    return responseToken.path("expires");
//  }

  public RequestSpecification prepareAuthRequest(RequestSpecification spec, String endpoint) {
    String token = getTokenResponse().path("token");
    return given(spec)
        //.auth().basic(authApi.basicAuth().getUsername(), authApi.basicAuth().getPassword())
        .auth().oauth2(token)
        .basePath(endpoint);
  }

  public RequestSpecification prepareBearerAuthRequest(RequestSpecification spec, String endpoint) {
    String token = getTokenResponse().path("token");
    return given(spec)
        .header("Authorization", "Bearer " + token)
        .basePath(endpoint);
  }

  public RequestSpecification prepareNotAuthRequest(RequestSpecification spec, String endpoint) {
    return given(spec)
        .basePath(endpoint);
  }




}
