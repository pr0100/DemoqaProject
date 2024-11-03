package UI.tests.forms;

import static helpers.config.TestData.firstName;
import static helpers.config.TestData.lastName;
import static helpers.config.TestData.mobileNumber;

import UI.steps.forms.PracticeFormSteps;
import helpers.config.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("UI")
@Feature("Тесты для вкладки Practice Form")
@DisplayName("Тесты для вкладки Practice Form")
@Tag("demoqaUI")
public class PracticeFormTests extends BaseTest {

  PracticeFormSteps practiceFormSteps = new PracticeFormSteps();

  @Test
  @DisplayName("Заполнение формы с минимальным количеством полей")
  void successRegistrationWithMinFields() {
    practiceFormSteps.goToPracticeFormPage();
    practiceFormSteps.fillMinAuthDataField(firstName, lastName, mobileNumber);
    practiceFormSteps.checkModalForm();
  }
}
