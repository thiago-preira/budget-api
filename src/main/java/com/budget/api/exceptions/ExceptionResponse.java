package com.budget.api.exceptions;

import java.util.Date;
import lombok.Data;
import lombok.Getter;

@Data
public class ExceptionResponse {

  @Getter
  private Date timestamp;
  @Getter
  private String message;
  @Getter
  private String details;

  public ExceptionResponse(Date timestamp, String message, String details) {
    this.timestamp = timestamp;
    this.message = message;
    this.details = details;
  }

}
