package helpers.config;

import com.github.javafaker.Faker;
import java.util.Random;

public class TestData {

  private static final Faker faker = new Faker();

  public static int getElemArrayBooks() {
    return faker.number().numberBetween(1, 7);
  }

  public static String generateRandomDigits(int length) {
    Random random = new Random();
    StringBuilder sb = new StringBuilder(length);

    for (int i = 0; i < length; i++) {
      int digit = random.nextInt(10);
      sb.append(digit);
    }

    return sb.toString();
  }

  public static String getSuccessfulUserName() {
    return faker.name().username();
  }

  public static String getSuccessfulPasswd() {
    return "1!s" + faker.internet().password(8, 15, true, true, true);
  }

  public static String getWrongPassword() {
    return faker.internet().password(5, 7);
  }

  public static String getSuccessfulFirstName() {
    return faker.name().firstName();
  }

  public static String getSuccessfulLastName() {
    return faker.name().lastName();
  }

  public static String getSuccessfulMobileNumber() {
    return generateRandomDigits(10);
  }

  public static String getWrongIsbn() {
    return String.valueOf(faker.number().numberBetween(10, 12));
  }

}
