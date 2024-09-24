package API.utils.models;

import java.util.List;
import lombok.Data;

@Data
public class AddBookRequestModel {
  String userId;
  List<IsbnModel> collectionOfIsbns;
}
