package API.utils.models;

import lombok.Data;

@Data
public class ErrorResponseModel {
  String code, message;
}