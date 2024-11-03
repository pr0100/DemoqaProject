package UI.steps.mainPage;


import UI.pages.mainPage.MainPage;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

public class MainPageSteps {

  MainPage mainPage = new MainPage();

  @Step("Перейти на главную страницу")
  public void goToMainPage() {
    mainPage.demoqaLink().scrollTo().click();
  }

  @Step("Перейти на страницу Form")
  public void goToFormsPage(){
    mainPage.formsLink().click();
  }

  @Step("Перейти на страницу Book Store")
  public void goToBookStorePage(){
    mainPage.bookStoreLink().shouldHave(Condition.visible).scrollTo().click();
  }

}
