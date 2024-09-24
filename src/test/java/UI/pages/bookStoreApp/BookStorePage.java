package UI.pages.bookStoreApp;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;

public class BookStorePage {

  @Description("Страница Login")
  public SelenideElement loginPageElement(){
    return $$("#item-0").get(5);
  }

  @Description("Страница Profile")
  public SelenideElement profilePageElement(){
    return $$("#item-3").get(4);
  }

  @Description("Поле выбора количества строк в таблице")
  public SelenideElement sizeRows() {
    return $("div.-center span select");
  }

  @Description("Количество строк в таблице")
  public ElementsCollection numberBooks() {
    return $(".rt-tbody").$$(".rt-tr-group");
  }

}
