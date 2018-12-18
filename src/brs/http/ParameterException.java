package brs.http;

import brs.CaretException;
import org.json.simple.JSONStreamAware;

public final class ParameterException extends CaretException {

  private final JSONStreamAware errorResponse;

  public ParameterException(JSONStreamAware errorResponse) {
    this.errorResponse = errorResponse;
  }

  JSONStreamAware getErrorResponse() {
    return errorResponse;
  }

}
