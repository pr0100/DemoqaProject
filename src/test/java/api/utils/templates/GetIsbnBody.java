package api.utils.templates;

import static api.utils.spec.Specification.requestSpecification;
import static helpers.config.Endpoints.BOOK;

import api.utils.models.IsbnModel;
import api.utils.wrapper.RestWrapper;
import helpers.config.TestData;
import java.util.ArrayList;
import java.util.List;

public class GetIsbnBody {

  RestWrapper restWrapper = new RestWrapper();

  public List<IsbnModel> getAvailableIsbn() {
    List<IsbnModel> isbnList = new ArrayList<>();
    IsbnModel isbn = new IsbnModel();
    isbn.setIsbn(restWrapper.get(requestSpecification(), "", BOOK)
        .getBodyFieldString("books[" + TestData.getElemArrayBooks() + "].isbn"));
    isbnList.add(isbn);
    return isbnList;
  }
}
