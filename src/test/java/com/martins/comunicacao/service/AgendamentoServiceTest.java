package com.martins.comunicacao.service;

import com.martins.comunicacao.dto.RequisicaoAgendamentoDto;
import com.martins.comunicacao.enumeration.ErrorCode;
import com.martins.comunicacao.enumeration.TipoComunicacao;
import com.martins.comunicacao.exception.AgendamentoException;
import com.martins.comunicacao.model.Agendamento;
import com.martins.comunicacao.repository.AgendamentoRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class AgendamentoServiceTest {

  @Autowired
  private AgendamentoService agendamentoService;
  @MockBean
  private AgendamentoRepository agendamentoRepository;

  @Test
  @DisplayName("Verifica se email ou celular está preenchido")
  public void requisicaoDeveTerEmailOuCelularPreenchido() {
    RequisicaoAgendamentoDto requisicao = new RequisicaoAgendamentoDto();
    requisicao.setDataHoraEnvio(LocalDateTime.now());
    requisicao.setNome("Nome");
    requisicao.setMensagem("Comunicacao");

    AgendamentoException exception = Assertions.assertThrows(
        AgendamentoException.class,
        () -> agendamentoService.criarAgendamento(requisicao));

    Assertions.assertEquals("É necessário que o email ou celular seja preenchido",
        exception.getMessage());
  }

  @Test
  @DisplayName("Verifica se o tipo de comunicação condiz com o destinatário")
  public void tipoComunicacaoDeveSerCondizenteComDestinatario() {
    RequisicaoAgendamentoDto requisicao = new RequisicaoAgendamentoDto();
    requisicao.setDataHoraEnvio(LocalDateTime.now().plusHours(3));
    requisicao.setNome("Nome");
    requisicao.setMensagem("Comunicacao");
    requisicao.setTipoComunicacao(TipoComunicacao.EMAIL);
    requisicao.setCelular("999999999999");

    AgendamentoException exception = Assertions.assertThrows(
        AgendamentoException.class,
        () -> agendamentoService.criarAgendamento(requisicao));

    Assertions.assertEquals("O tipo de comunicacao EMAIL requer que o email seja preenchido",
        exception.getMessage());
  }

  @Test
  @DisplayName("Verifica se eh uma data futura")
  public void agendamentoDeveSerEmUmaDataFutura() {
    RequisicaoAgendamentoDto requisicao = new RequisicaoAgendamentoDto();
    requisicao.setDataHoraEnvio(LocalDateTime.now().minusDays(1));
    requisicao.setNome("Nome");
    requisicao.setMensagem("Comunicacao");
    requisicao.setTipoComunicacao(TipoComunicacao.EMAIL);
    requisicao.setEmail("teste@gmail.com");
    requisicao.setCelular("999999999");

    AgendamentoException exception = Assertions.assertThrows(
        AgendamentoException.class,
        () -> agendamentoService.criarAgendamento(requisicao));

    Assertions.assertEquals("Data inválida. A data de envio de ser uma data futura",
        exception.getMessage());
  }

  @Test
  @DisplayName("Teste criacao de agendamento")
  public void testeCriacaoAgendamento() {
    RequisicaoAgendamentoDto requisicao = new RequisicaoAgendamentoDto();
    requisicao.setDataHoraEnvio(LocalDateTime.now().plusDays(10));
    requisicao.setNome("Nome");
    requisicao.setMensagem("Comunicacao");
    requisicao.setTipoComunicacao(TipoComunicacao.EMAIL);
    requisicao.setEmail("teste@gmail.com");
    requisicao.setCelular("999999999");

    Agendamento agendamento = requisicao.paraEntidade();

    Mockito.doReturn(agendamento)
        .when(agendamentoRepository).save(ArgumentMatchers.any(Agendamento.class));

    agendamentoService.criarAgendamento(requisicao);

    Mockito.verify(agendamentoRepository,
        Mockito.times(1))
        .save(ArgumentMatchers.any(Agendamento.class));

  }

  @Test
  @DisplayName("Verifica se a data eh futura")
  public void dataDeEnvioDeveSerFutura() {
    RequisicaoAgendamentoDto requisicao = new RequisicaoAgendamentoDto();
    requisicao.setDataHoraEnvio(LocalDateTime.now().minusDays(1));
    requisicao.setNome("Nome");
    requisicao.setMensagem("Comunicacao");
    requisicao.setTipoComunicacao(TipoComunicacao.EMAIL);
    requisicao.setCelular("999999999999");

    AgendamentoException exception = Assertions.assertThrows(
        AgendamentoException.class,
        () -> agendamentoService.criarAgendamento(requisicao));

    Assertions.assertEquals(ErrorCode.ERRO_DATA_INVALIDA.getMensagem(),
        exception.getMessage());
  }
}
