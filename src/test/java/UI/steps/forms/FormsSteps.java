package UI.steps.forms;

import UI.pages.forms.FormsPage;
import io.qameta.allure.Step;

public class FormsSteps {

  FormsPage formsPage = new FormsPage();

  @Step
  public void goToPracticeFormPage(){
    formsPage.practiceFormsElement().click();
  }

}
