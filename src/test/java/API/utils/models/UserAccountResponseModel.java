package API.utils.models;

import java.util.List;
import lombok.Data;

@Data
public class UserAccountResponseModel {

  String userID, username;
  List<BooksModel> books;

}
