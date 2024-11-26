package ui.tests.forms;


import helpers.config.TestData;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import ui.steps.forms.PracticeFormSteps;
import helpers.utils.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("ui")
@Feature("Тесты для вкладки Practice Form")
@Tag("ui")
public class PracticeFormTests extends BaseTest {

  PracticeFormSteps practiceFormSteps = new PracticeFormSteps();

  @Test
  @Severity(SeverityLevel.BLOCKER)
  @DisplayName("Заполнение формы с минимальным количеством полей")
  void successRegistrationWithMinFields() {
    practiceFormSteps.goToPracticeFormPage();
    practiceFormSteps.fillMinAuthDataField(TestData.getSuccessfulFirstName(), TestData.getSuccessfulLastName(), TestData.getSuccessfulMobileNumber());
    practiceFormSteps.checkModalForm();
  }

}
