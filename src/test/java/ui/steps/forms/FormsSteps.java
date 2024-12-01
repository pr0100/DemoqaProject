package ui.steps.forms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ui.pages.forms.FormsPage;
import io.qameta.allure.Step;

public class FormsSteps {

  FormsPage formsPage = new FormsPage();
  protected static final Logger LOGGER = LogManager.getLogger();

  @Step("Перейти на страницу Practice Form")
  public void goToPracticeFormPage(){
    LOGGER.info("Practice Form opened");
    formsPage.practiceFormElement().click();
  }

}
