package com.martins.comunicacao.service;

import static com.martins.comunicacao.enumeration.ErrorCode.ERRO_AGENDAMENTO_INEXISTENTE;
import static com.martins.comunicacao.enumeration.ErrorCode.ERRO_REMOVER_AGENDAMENTO;

import com.martins.comunicacao.dto.RequisicaoAgendamentoDto;
import com.martins.comunicacao.dto.RespostaStatusAgendamentoDto;
import com.martins.comunicacao.exception.AgendamentoException;
import com.martins.comunicacao.model.Agendamento;
import com.martins.comunicacao.repository.AgendamentoRepository;
import com.martins.comunicacao.validator.AgendamentoValidator;
import java.util.Optional;
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
            () -> new AgendamentoException(ERRO_AGENDAMENTO_INEXISTENTE.getCodigo(),
                ERRO_AGENDAMENTO_INEXISTENTE.getMensagem())));
  }

  public void removeAgendamento(final Long agendamentoId) {
    log.info("Removendo agendamento id: " + agendamentoId);
    try {
      repository.deleteById(agendamentoId);
    } catch (EmptyResultDataAccessException e) {
      throw new AgendamentoException(ERRO_REMOVER_AGENDAMENTO.getCodigo(),
          String.format(ERRO_REMOVER_AGENDAMENTO.getMensagem(), agendamentoId));
    }
  }

  private void validaDadosDeEntrada(final RequisicaoAgendamentoDto requisicao) {
    AgendamentoValidator.validaDadosDestinatario(requisicao);
    AgendamentoValidator.validaData(requisicao.getDataHoraEnvio());
    AgendamentoValidator.validaTipoComunicacao(requisicao);
  }
}
