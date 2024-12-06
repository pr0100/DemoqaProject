package ui.pages.forms;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;

public class PracticeFormPage {

  public SelenideElement firstNameElement(){
    return $("#firstName").as("Поле ввода First Name");
  }

  public SelenideElement lastNameElement(){
    return $("#lastName").as("Поле ввода Last Name");
  }

  public SelenideElement genderMaleElement(){
    return $("#gender-radio-1").as("Поле ввода Gender Male");
  }

  public SelenideElement mobileNumberElement(){
    return $("#userNumber").as("Поле ввода Mobile Number");
  }

  public SelenideElement submitButton(){
    return $("#submit").as("Кнопка Submit");
  }

  public SelenideElement modalTitle(){
    return $("#example-modal-sizes-title-lg").as("Название модального окна");
  }

  public SelenideElement modalStudentName() {
    return $x("//table//td[.='Student Name']/following-sibling::td").as("Имя студента");
  }

  public SelenideElement modalMobile() {
    return $x("//table//td[.='Mobile']/following-sibling::td").as("Номер телефона");
  }
}
