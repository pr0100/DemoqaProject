package api.utils.templates;

import api.utils.models.IsbnModel;
import java.util.ArrayList;
import java.util.List;

public class GetIsbnBody {

  public List<IsbnModel> getAvailableIsbn(String isbn) {
    List<IsbnModel> isbnList = new ArrayList<>();
    IsbnModel model = new IsbnModel();
    model.setIsbn(isbn);
    isbnList.add(model);
    return isbnList;
  }
}
