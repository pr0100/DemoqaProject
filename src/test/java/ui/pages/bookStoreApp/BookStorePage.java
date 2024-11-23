package ui.pages.bookStoreApp;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class BookStorePage {

  public SelenideElement loginPageElement(){
    return $x("//span[.='Login']").as("Страница Login");
  }

  public SelenideElement profilePageElement(){
    return $x("//span[.='Profile']").as("Страница Profile");
  }

  public SelenideElement sizeRows() {
    return $("div.-center span select").as("Поле выбора количества строк в таблице");
  }

  public ElementsCollection numberBooks() {
    return $(".rt-tbody").$$(".rt-tr-group").as("Количество строк в таблице");
  }

}
