package UI.pages.mainPage;


import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;

public class MainPage {

  @Description("Переход на страницу Forms")
  public SelenideElement formsLink(){
    return $("div.home-body > div > div:nth-child(2)");
  }

  @Description("Переход на страницу Book Store Application")
  public SelenideElement bookStoreLink(){
    return $("div.home-body > div > div:nth-child(6)");
  }

}
