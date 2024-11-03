package api.utils.models;

import java.util.Date;
import lombok.Data;

@Data
public class BooksModel {
  String isbn, title, subTitle, author, publisher, description, website;
  Date publish_date;
  int pages;
}
