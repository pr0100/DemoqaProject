package UI.tests.forms;

import static helpers.config.TestData.date;
import static helpers.config.TestData.firstName;
import static helpers.config.TestData.lastName;
import static helpers.config.TestData.mobileNumber;

import UI.steps.forms.PracticeFormSteps;
import helpers.config.TestBase;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тесты вкладки Practice Form")
public class PracticeFormTests extends TestBase {

  PracticeFormSteps practSteps = new PracticeFormSteps();

  @Test
  @DisplayName("Заполнение формы с минимальным количеством полей")
  void successRegistrationWithMinFields() {
    practSteps.goToPracticeFormPage();
    practSteps.fillMinAuthDataField(firstName, lastName, mobileNumber);
    practSteps.checkModalForm();
  }

  @Disabled
  @Test
  void successRegistrationWithMaxFields() {
    practSteps.goToPracticeFormPage();
    practSteps.fillInTheFirstNameField(firstName);
    practSteps.fillInTheLastNameField(lastName);
    practSteps.chooseTheGenderField();
    practSteps.fillInTheMobileField(mobileNumber);
    practSteps.fillInTheDateOfBirthField(date);
    practSteps.clickSubmit();
    practSteps.checkModalForm();
  }
}
