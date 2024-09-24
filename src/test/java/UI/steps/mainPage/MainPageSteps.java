package UI.steps.mainPage;


import UI.pages.mainPage.MainPage;
import io.qameta.allure.Step;

public class MainPageSteps {

  MainPage mainPage = new MainPage();

  @Step
  public void goToFormsPage(){
    mainPage.formsLink().click();
  }

  @Step("Перейти на страницу Book Store")
  public void goToBookStorePage(){
    mainPage.bookStoreLink().click();
  }

}
