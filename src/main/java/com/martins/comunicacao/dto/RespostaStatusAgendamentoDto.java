package com.martins.comunicacao.dto;

import com.martins.comunicacao.enumeration.StatusComunicacao;
import com.martins.comunicacao.model.Agendamento;

public class RespostaStatusAgendamentoDto {
  private StatusComunicacao status;


  public static RespostaStatusAgendamentoDto paraDto(Agendamento agendamento) {
    RespostaStatusAgendamentoDto resposta = new RespostaStatusAgendamentoDto();
    resposta.setStatus(agendamento.getStatus());

    return resposta;
  }

  public StatusComunicacao getStatus() {
    return status;
  }

  public void setStatus(StatusComunicacao status) {
    this.status = status;
  }
}
