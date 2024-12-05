package api.utils;

import java.util.List;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class CustomMatchers {
  public static Matcher<String> emptyOrNullOrEmptyArray() {
    return new BaseMatcher<>() {
      @Override
      public boolean matches(Object item) {
        return item == null || (item instanceof List && ((List<?>) item).isEmpty());
      }

      @Override
      public void describeTo(Description description) {
        description.appendText("null or empty array");
      }
    };
  }

}
