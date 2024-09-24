package UI.pages.bookStoreApp;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;

public class LoginPage {

  @Description("Поле ввода UserName")
  public SelenideElement userNameElement(){
    return $("#userName");
  }

  @Description("Поле ввода Password")
  public SelenideElement passwordElement(){
    return $("#password");
  }

  @Description("Кнопка Login")
  public SelenideElement loginBtnElement(){
    return $("#login");
  }

  @Description("Кнопка New User")
  public SelenideElement newUserBtnElement(){
    return $("#newUser");
  }

  @Description("Поле вывода ошибки")
  public SelenideElement outputMessageElement(){
    return $("#output");
  }


}
