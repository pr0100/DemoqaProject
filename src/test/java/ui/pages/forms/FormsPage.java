package ui.pages.forms;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class FormsPage {

  public SelenideElement practiceFormElement(){
    return Selenide.$x("//span[.='Practice Form']").as("Страница Practice Form");
  }

}
