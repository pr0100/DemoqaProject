package api.utils.templates;


import api.steps.HelpSteps;
import api.utils.models.IsbnModel;
import java.util.ArrayList;
import java.util.List;

public class GetIsbnBody {

  HelpSteps helpSteps = new HelpSteps();

  public List<IsbnModel> getAvailableIsbn() {
    List<IsbnModel> isbnList = new ArrayList<>();
    IsbnModel isbn = new IsbnModel();
    isbn.setIsbn(helpSteps.getIsbn());
    isbnList.add(isbn);
    return isbnList;
  }
}
