package helpers.auth;

public class AuthCred {

  private String username;
  private String password;
  private String userID;

  public AuthCred(String username, String password, String userID) {
    this.username = username;
    this.password = password;
    this.userID = userID;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getUserID() {
    return userID;
  }
}
