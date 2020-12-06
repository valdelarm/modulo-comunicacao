package com.martins.comunicacao.service;

import com.martins.comunicacao.dto.RequisicaoAgendamentoDto;
import com.martins.comunicacao.enumeration.TipoComunicacao;
import com.martins.comunicacao.repository.AgendamentoRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

    IllegalArgumentException exception = Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> agendamentoService.criarAgendamento(requisicao));

    Assertions.assertEquals("É necessário que o email ou celular seja preenchido",
        exception.getMessage());
  }

  @Test
  @DisplayName("Verifica se o tipo de comunicação condiz com o destinatário")
  public void tipoComunicacaoDeveSerCondizenteComDestinatario() {
    RequisicaoAgendamentoDto requisicao = new RequisicaoAgendamentoDto();
    requisicao.setDataHoraEnvio(LocalDateTime.now());
    requisicao.setNome("Nome");
    requisicao.setMensagem("Comunicacao");
    requisicao.setTipoComunicacao(TipoComunicacao.EMAIL);
    requisicao.setCelular("999999999999");

    IllegalArgumentException exception = Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> agendamentoService.criarAgendamento(requisicao));

    Assertions.assertEquals("O tipo de comunicacao EMAIL requer que o email seja preenchido",
        exception.getMessage());
  }
}
