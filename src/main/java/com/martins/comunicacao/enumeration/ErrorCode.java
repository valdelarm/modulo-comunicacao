package com.martins.comunicacao.enumeration;

public enum ErrorCode {
  ERRO_DESCONHECIDO("-1", "Erro desconhecido"),
  ERRO_AGENDAMENTO_INEXISTENTE("01", "Não existe agendamento para o destinatário solicitado"),
  ERRO_REMOVER_AGENDAMENTO("02", "Não há agendamento para remover com o ID %d"),
  ERRO_TIPO_COMUNICACAO("03", "O tipo de comunicacao EMAIL requer que o email seja preenchido"),
  ERRO_DATA_INVALIDA("04", "Data inválida. A data de envio de ser uma data futura"),
  ERRO_DESTINATARIO_OBRIGATORIO("05", "É necessário que o email ou celular seja preenchido"),;

  private final String codigo;
  private final String mensagem;

  ErrorCode(String codigo, String mensagem) {
    this.codigo = codigo;
    this.mensagem = mensagem;
  }

  public String getCodigo() {
    return codigo;
  }

  public String getMensagem() {
    return mensagem;
  }
}
