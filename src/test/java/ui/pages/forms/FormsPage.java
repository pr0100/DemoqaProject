package ui.pages.forms;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.SelenideElement;

public class FormsPage {

  public SelenideElement practiceFormElement(){
    return $$("#item-0").get(1).as("Страница Practice Form");
  }

}
