package com.martins.comunicacao.exception;

public class AgendamentoException extends RuntimeException {
  private String errorCode;

  public AgendamentoException(String errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
  }

  public String getErrorCode() {
    return errorCode;
  }
}
