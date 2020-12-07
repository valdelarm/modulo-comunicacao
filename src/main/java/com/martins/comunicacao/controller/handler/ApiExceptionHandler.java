package com.martins.comunicacao.controller.handler;

import com.martins.comunicacao.dto.ApiErroDto;
import com.martins.comunicacao.enumeration.ErrorCode;
import com.martins.comunicacao.exception.AgendamentoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * This class is responsible to handle all the exceptions
 * and provide a custom response.
 * **/
@RestControllerAdvice
public class ApiExceptionHandler {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @ResponseBody
  @ExceptionHandler(AgendamentoException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiErroDto handleAgendamentoException(AgendamentoException ex) {
    commonLog(ex);
    return new ApiErroDto(ex.getErrorCode(), ex.getMessage());
  }

  @ResponseBody
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ApiErroDto handleException(Exception ex) {
    log.error("Erro desconhecido " + ex.getMessage());
    return new ApiErroDto(ErrorCode.ERRO_DESCONHECIDO.getCodigo(),
        ErrorCode.ERRO_DESCONHECIDO.getMensagem());
  }

  private void commonLog(Exception ex) {
    log.error(ex.getClass().getSimpleName() + " erro " + ex.getMessage());
  }
}
