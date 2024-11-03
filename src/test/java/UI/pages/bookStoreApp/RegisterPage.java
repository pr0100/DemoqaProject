package UI.pages.bookStoreApp;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;

public class RegisterPage {

  public SelenideElement titlePage() {
    return $("#text-center").as("Заголовок страницы");
  }
}
