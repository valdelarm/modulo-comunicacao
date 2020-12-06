package com.martins.comunicacao.service;

import com.martins.comunicacao.dto.RequisicaoAgendamentoDto;
import com.martins.comunicacao.enumeration.TipoComunicacao;
import com.martins.comunicacao.model.Agendamento;
import com.martins.comunicacao.repository.AgendamentoRepository;
import static java.util.Objects.isNull;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoService {

  private final AgendamentoRepository repository;

  public AgendamentoService(AgendamentoRepository repository) {
    this.repository = repository;
  }

  public Agendamento criarAgendamento(final RequisicaoAgendamentoDto requisicao) {
    validaDadosDeEntrada(requisicao);
    return repository.save(requisicao.paraEntidade());
  }

  private void validaDadosDeEntrada(final RequisicaoAgendamentoDto requisicao) {
    validaDadosDestinatario(requisicao);
    if (TipoComunicacao.EMAIL.equals(requisicao.getTipoComunicacao())
        && (isNull(requisicao.getEmail()) || requisicao.getEmail().isBlank())) {
      throw new IllegalArgumentException("O tipo de comunicacao EMAIL requer que o email seja preenchido");
    }
  }

  private void validaDadosDestinatario(final RequisicaoAgendamentoDto requisicao) {
    if (destinatarioEhNull(requisicao)
        || (requisicao.getCelular().isBlank() && requisicao.getEmail().isBlank())) {
      throw new IllegalArgumentException("É necessário que o email ou celular seja preenchido");
    }
  }

  private boolean destinatarioEhNull(final RequisicaoAgendamentoDto requisicao) {
    return isNull(requisicao.getCelular()) && isNull(requisicao.getEmail());
  }
}
