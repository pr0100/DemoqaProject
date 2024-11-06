package ui.pages.bookStoreApp;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class RegisterPage {

  public SelenideElement titlePage() {
    return $("#text-center").as("Заголовок страницы");
  }
}
