package UI.pages.forms;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;

public class PracticeFormPage {

  @Description("Поле ввода First Name")
  public SelenideElement firstNameElement(){
    return $("#firstName");
  }

  @Description("Поле ввода Last Name")
  public SelenideElement lastNameElement(){
    return $("#lastName");
  }

  @Description("Поле ввода Email")
  public SelenideElement emailElement(){
    return $("#userEmail");
  }

  @Description("Поле ввода Gender Male")
  public SelenideElement genderMaleElement(){
    return $("#gender-radio-1");
  }

  @Description("Поле ввода Gender Female")
  public SelenideElement genderFemaleElement(){
    return $("#gender-radio-2");
  }

  @Description("Поле ввода Mobile Number")
  public SelenideElement mobileNumberElement(){
    return $("#userNumber");
  }

  @Description("Поле ввода Date of Birth")
  public SelenideElement dateBirthElement(){
    return $("#dateOfBirthInput");
  }

  public SelenideElement datePickerMonth(){
    return $(".react-datepicker__month-select");
  }

  public SelenideElement datePickerYear(){
    return $(".react-datepicker__year-select");
  }

  public SelenideElement datePickerDay(String day){
    return $x("//div[contains(@class, 'react-datepicker__day') and text()=" + day + " ]");
  }


  @Description("Поле ввода Subjects")
  public SelenideElement subjectsElement(){
    return $("#subjectsInput");
  }

  @Description("Поле ввода Hobbies Sport")
  public SelenideElement hobbiesSportElement(){
    return $("#hobbies-checkbox-1");
  }

  @Description("Поле ввода Picture")
  public SelenideElement pictureElement(){
    return $("input[type='file']");
  }

  @Description("Поле ввода Address")
  public SelenideElement addressElement(){
    return $("#currentAddress");
  }

  @Description("Поле ввода State")
  public SelenideElement stateElement(){
    return $("#state");
  }

  @Description("Поле ввода City")
  public SelenideElement cityElement(){
    return $("#city");
  }

  @Description("Кнопка Submit")
  public SelenideElement submitButton(){
    return $("#submit");
  }

  @Description("Название модального окна")
  public SelenideElement modalTitle(){
    return $("#example-modal-sizes-title-lg");
  }
}
