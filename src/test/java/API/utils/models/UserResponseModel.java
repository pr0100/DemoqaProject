package API.utils.models;

import java.util.List;
import lombok.Data;

@Data
public class UserResponseModel {

  String userId, username;
  List<BooksModel> books;
}
