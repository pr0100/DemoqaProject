package helpers.config;

import com.github.javafaker.Faker;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Random;

public class TestData {

  static SimpleDateFormat sdf = new SimpleDateFormat("MMM/dd/yyyy", Locale.ENGLISH);
  private static final Faker faker = new Faker();
  public static String defaultUserName = "SSStas";
  public static String defaultPassword = "!Password123";
  public static String defaultUserID = "f5b9a0cc-5528-4cf6-a626-05bd954ce36d";
  public static String userName = faker.name().username();
  public static String password = faker.internet().password(8, 15, true, true, true);
  public static String wrongPassword = faker.internet().password(5, 7);
  public static String firstName = faker.name().firstName();
  public static String lastName = faker.name().lastName();
  public static String email = faker.internet().emailAddress();
  public static String mobileNumber = generateRandomDigits(10);
  public static String date = sdf.format(faker.date().birthday());
  public static String address = faker.address().fullAddress();
  //public static int elemArrayBooks = faker.number().numberBetween(0, 7);
  public static String wrongIsbn = String.valueOf(faker.number().numberBetween(10, 12));

  public static int getElemArrayBooks() {
    return faker.number().numberBetween(0, 7);
  }

  public static String generateRandomDigits(int length) {
    Random random = new Random();
    StringBuilder sb = new StringBuilder(length);

    for (int i = 0; i < length; i++) {
      int digit = random.nextInt(10); // Генерируем случайную цифру от 0 до 9
      sb.append(digit);
    }

    return sb.toString();
  }

  public static String getSuccessfulUserName() {
    return faker.name().username();
  }

  public static String getSuccessfulPasswd() {
    return faker.internet().password(8, 15, true, true, true);
  }
}
