package UI.pages.bookStoreApp;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;

public class ProfilePage {

  @Description("Кнопка Log out")
  public SelenideElement logOutBtn() {
    return $x("//button[.='Log out']");
  }

  @Description("Имя пользователя")
  public SelenideElement userNameLabel() {
    return $("#userName-value");
  }

  @Description("Текст для неавторизованных пользователей")
  public SelenideElement notLoggingLabel() {
    return $("#notLoggin-label");
  }

  @Description("Поле поиска книги")
  public SelenideElement searchBookInput() {
    return $("#searchBox");
  }

  @Description("Текст для пустой таблицы")
  public SelenideElement emptyTableLabel() {
    return $(".rt-noData");
  }

}
