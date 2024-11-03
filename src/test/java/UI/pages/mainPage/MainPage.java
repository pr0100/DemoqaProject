package UI.pages.mainPage;


import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;

public class MainPage {

  public SelenideElement demoqaLink() {
    return $("header > a").as("Ссылка на главную страницу");
  }

  public SelenideElement formsLink(){
    return $("div.home-body > div > div:nth-child(2)").as("Страница Forms");
  }

  public SelenideElement bookStoreLink(){
    return $("div.home-body > div > div:nth-child(6)").as("Страница Book Store Application");
  }

}
