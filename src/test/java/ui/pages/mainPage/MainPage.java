package ui.pages.mainPage;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;

public class MainPage {

  public SelenideElement demoqaLink() {
    return $("header > a").as("Ссылка на главную страницу");
  }

  public SelenideElement formsLink(){
    return $x("//h5[.='Forms']").as("Страница Forms");
  }

  public SelenideElement bookStoreLink(){
    return $x("//h5[.='Book Store Application']").as("Страница Book Store Application");
  }

}
