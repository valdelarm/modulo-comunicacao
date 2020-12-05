package com.martins.comunicacao.service;

import com.martins.comunicacao.dto.RequisicaoAgendamentoDto;
import com.martins.comunicacao.model.Agendamento;
import com.martins.comunicacao.repository.AgendamentoRepository;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoService {

  private final AgendamentoRepository repository;

  public AgendamentoService(AgendamentoRepository repository) {
    this.repository = repository;
  }

  public Agendamento criarAgendamento(RequisicaoAgendamentoDto requisicao) {
    return repository.save(requisicao.paraEntidade());
  }
}
