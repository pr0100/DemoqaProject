package ui.pages.forms;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class PracticeFormPage {

  public SelenideElement firstNameElement(){
    return $("#firstName").as("Поле ввода First Name");
  }

  public SelenideElement lastNameElement(){
    return $("#lastName").as("Поле ввода Last Name");
  }

  public SelenideElement emailElement(){
    return $("#userEmail").as("Поле ввода Email");
  }

  public SelenideElement genderMaleElement(){
    return $("#gender-radio-1").as("Поле ввода Gender Male");
  }

  public SelenideElement genderFemaleElement(){
    return $("#gender-radio-2").as("Поле ввода Gender Female");
  }

  public SelenideElement mobileNumberElement(){
    return $("#userNumber").as("Поле ввода Mobile Number");
  }

  public SelenideElement dateBirthElement(){
    return $("#dateOfBirthInput").as("Поле ввода Date of Birth");
  }

  public SelenideElement subjectsElement(){
    return $("#subjectsInput").as("Поле ввода Subjects");
  }

  public SelenideElement hobbiesSportElement(){
    return $("#hobbies-checkbox-1").as("Поле ввода Hobbies Sport");
  }

  public SelenideElement pictureElement(){
    return $("input[type='file']").as("Поле ввода Picture");
  }

  public SelenideElement addressElement(){
    return $("#currentAddress").as("Поле ввода Address");
  }

  public SelenideElement stateElement(){
    return $("#state").as("Поле ввода State");
  }

  public SelenideElement cityElement(){
    return $("#city").as("Поле ввода City");
  }

  public SelenideElement submitButton(){
    return $("#submit").as("Кнопка Submit");
  }

  public SelenideElement modalTitle(){
    return $("#example-modal-sizes-title-lg").as("Название модального окна");
  }
}
