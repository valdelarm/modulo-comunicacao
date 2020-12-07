package com.martins.comunicacao.dto;

public class ApiErroDto {
  private final String codigo;
  private final String erro;

  public ApiErroDto(String codigo, String erro) {
    this.codigo = codigo;
    this.erro = erro;
  }

  public String getCodigo() {
    return codigo;
  }

  public String getErro() {
    return erro;
  }
}