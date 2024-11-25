package helpers.config;

import org.aeonbits.owner.Config;

@Config.Sources("file:src/test/resources/config.properties")
public interface WebConfig extends Config {

  @Key("page.load.strategy")
  String pageLoadStrategy();

  @Key("base.url")
  String baseUrl();

  @Key("user.username")
  @DefaultValue("SSStas")
  String getUserName();

  @Key("user.password")
  @DefaultValue("!Password123")
  String getPassword();

  @Key("book.isbn")
  @DefaultValue("9781449325862")
  String getAvailableIsbn();
}
