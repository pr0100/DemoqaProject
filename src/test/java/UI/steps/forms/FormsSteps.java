package UI.steps.forms;

import UI.pages.forms.FormsPage;
import io.qameta.allure.Step;

public class FormsSteps {

  FormsPage formsPage = new FormsPage();

  @Step("Перейти на страницу Practice Form")
  public void goToPracticeFormPage(){
    formsPage.practiceFormElement().click();
  }

}
