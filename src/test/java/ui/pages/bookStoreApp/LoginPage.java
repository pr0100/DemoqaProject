package ui.pages.bookStoreApp;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class LoginPage {

  public SelenideElement userNameElement(){
    return $("#userName").as("Поле ввода UserName");
  }

  public SelenideElement passwordElement(){
    return $("#password").as("Поле ввода Password");
  }

  public SelenideElement loginBtnElement(){
    return $("#login").as("Кнопка Login");
  }

  public SelenideElement newUserBtnElement(){
    return $("#newUser").as("Кнопка New User");
  }

  public SelenideElement outputMessageElement(){
    return $("#output").as("Поле вывода ошибки");
  }


}
