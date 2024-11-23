package ui.pages.bookStoreApp;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;

public class ProfilePage {

  public SelenideElement logOutBtn() {
    return $x("//button[.='Log out']").as("Кнопка Log out");
  }

  public SelenideElement userNameLabel() {
    return $("#userName-value").as("Имя пользователя");
  }

  public SelenideElement notLoggingLabel() {
    return $("#notLoggin-label").as("Текст для неавторизованных пользователей");
  }

  public SelenideElement searchBookInput() {
    return $("#searchBox").as("Поле поиска книги");
  }

  public SelenideElement emptyTableLabel() {
    return $(".rt-noData").as("Текст для пустой таблицы");
  }

  public SelenideElement deleteRecordBtn() {
    return $("#delete-record-undefined").as("Кнопка удаления книги");
  }

  public SelenideElement modalFormOkBtn() {
    return $("#closeSmallModal-ok").as("Кнопка 'ОК' в окне");
  }

  public SelenideElement deleteAllRecordsBtn() {
    return $x("//button[.='Delete All Books']").as("Кнопка удаления всех книг");
  }
}
