package com.martins.comunicacao.service;

import com.martins.comunicacao.dto.RequisicaoAgendamentoDto;
import com.martins.comunicacao.dto.RespostaStatusAgendamentoDto;
import com.martins.comunicacao.enumeration.TipoComunicacao;
import com.martins.comunicacao.model.Agendamento;
import com.martins.comunicacao.repository.AgendamentoRepository;
import static java.util.Objects.isNull;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoService {

  private final AgendamentoRepository repository;
  private Logger log = LoggerFactory.getLogger(this.getClass());

  public AgendamentoService(AgendamentoRepository repository) {
    this.repository = repository;
  }

  public Agendamento criarAgendamento(final RequisicaoAgendamentoDto requisicao) {
    log.info("Criacao de agendamento para: " + requisicao);
    validaDadosDeEntrada(requisicao);
    return repository.save(requisicao.paraEntidade());
  }

  public RespostaStatusAgendamentoDto recuperarAgendamento(final Long agendamentoId) {
    log.info("Recuperando agendamento id: " + agendamentoId);
    Optional<Agendamento> agendamento = repository.findById(agendamentoId);

    return RespostaStatusAgendamentoDto.paraDto(
        agendamento.orElseThrow(
            () -> new NoSuchElementException("Nao existe agendamento para o destinatário solicitado")));
  }

  public void removeAgendamento(final Long agendamentoId) {
    log.info("Removendo agendamento id: " + agendamentoId);
    try {
      repository.deleteById(agendamentoId);
    } catch (EmptyResultDataAccessException e) {
      throw new NoResultException("Não há agendamento para remover com o ID " + agendamentoId);
    }
  }

  private void validaDadosDeEntrada(final RequisicaoAgendamentoDto requisicao) {
    validaDadosDestinatario(requisicao);
    ehUmaDataFutura(requisicao.getDataHoraEnvio());
    if (TipoComunicacao.EMAIL.equals(requisicao.getTipoComunicacao())
        && (isNull(requisicao.getEmail()) || requisicao.getEmail().isBlank())) {
      throw new IllegalArgumentException("O tipo de comunicacao EMAIL requer que o email seja preenchido");
    }
  }

  /**
   * Verifica se a data e hora informada é futura.
   * @param dataHoraEnvio
   */
  private void ehUmaDataFutura(LocalDateTime dataHoraEnvio) {
    if (dataHoraEnvio.isBefore(LocalDateTime.now())) {
      throw new IllegalArgumentException("Data inválida. A data de envio de ser uma data futura");
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
