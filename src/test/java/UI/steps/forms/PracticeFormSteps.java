package UI.steps.forms;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.executeJavaScript;

import UI.pages.forms.PracticeFormPage;
import UI.steps.mainPage.MainPageSteps;
import io.qameta.allure.Step;

public class PracticeFormSteps {

  private final MainPageSteps mainPageSteps = new MainPageSteps();
  private final FormsSteps formsSteps = new FormsSteps();
  private final PracticeFormPage practiceFormPage = new PracticeFormPage();

  @Step("Переход на страницу 'Practice Form'")
  public void goToPracticeFormPage(){
    mainPageSteps.goToFormsPage();
    formsSteps.goToPracticeFormPage();
  }

  @Step("Заполнить поле 'First Name'")
  public void fillInTheFirstNameField(String name) {
    practiceFormPage.firstNameElement().sendKeys(name);
  }

  @Step("Заполнить поле 'Last Name'")
  public void fillInTheLastNameField(String name) {
    practiceFormPage.lastNameElement().sendKeys(name);
  }

  @Step("Заполнить поле 'Email'")
  public void fillInTheEmailField(String email) {
    practiceFormPage.emailElement().sendKeys(email);
  }

  @Step("Заполнить поле 'Gender Male'")
  public void chooseTheGenderField() {
    executeJavaScript("arguments[0].click();", practiceFormPage.genderMaleElement());
  }

  @Step("Заполнить поле 'Mobile'")
  public void fillInTheMobileField(String phone) {
    practiceFormPage.mobileNumberElement().sendKeys(phone);
  }

  @Step("Заполнить поле 'Date of Birth'")
  public void fillInTheDateOfBirthField(String date) {
    practiceFormPage.dateBirthElement().clear();
    practiceFormPage.dateBirthElement().setValue(date);
  }

  @Step("Заполнить поле 'Hobbies'")
  public void fillInTheHobbieField() {
    practiceFormPage.hobbiesSportElement().click();
  }

  @Step("Заполнить поле 'Current Address'")
  public void fillInTheAddressField(String address) {
    practiceFormPage.addressElement().sendKeys(address);
  }

  @Step("Нажать кнопку 'Submit'")
  public void clickSubmit(){
    practiceFormPage.submitButton().scrollTo().click();
  }

  @Step("Проверить название модального окна")
  public void checkModalForm(){
    practiceFormPage.modalTitle().shouldHave(text("Thanks for submitting the form"));
  }

  @Step("Заполнить минимальное количество полей на форме и нажать кнопку Submit")
  public void fillMinAuthDataField(String firstName, String lastName, String mobileNumber){
    fillInTheFirstNameField(firstName);
    fillInTheLastNameField(lastName);
    chooseTheGenderField();
    fillInTheMobileField(mobileNumber);
    clickSubmit();
  }
}
