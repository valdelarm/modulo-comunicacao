package com.martins.comunicacao.validator;

import static com.martins.comunicacao.enumeration.ErrorCode.ERRO_DATA_INVALIDA;
import static com.martins.comunicacao.enumeration.ErrorCode.ERRO_DESTINATARIO_OBRIGATORIO;
import static com.martins.comunicacao.enumeration.ErrorCode.ERRO_TIPO_COMUNICACAO;
import static java.util.Objects.isNull;

import com.martins.comunicacao.dto.RequisicaoAgendamentoDto;
import com.martins.comunicacao.enumeration.TipoComunicacao;
import com.martins.comunicacao.exception.AgendamentoException;
import java.time.LocalDateTime;

public class AgendamentoValidator {


  /**
   * Verifica se a data e hora informada é futura.
   * @param dataHoraEnvio
   */
  public static void validaData(final LocalDateTime dataHoraEnvio) {
    if (dataHoraEnvio.isBefore(LocalDateTime.now())) {
      throw new AgendamentoException(ERRO_DATA_INVALIDA.getCodigo(),
          ERRO_DATA_INVALIDA.getMensagem());
    }
  }

  /**
   * Verifica se o tipo de comunicacao condiz com os dados preenchidos
   * @param requisicao
   */
  public static void validaTipoComunicacao(final RequisicaoAgendamentoDto requisicao) {
    if (TipoComunicacao.EMAIL.equals(requisicao.getTipoComunicacao())
        && (isNull(requisicao.getEmail()) || requisicao.getEmail().isBlank())) {
      throw new AgendamentoException(ERRO_TIPO_COMUNICACAO.getCodigo(), ERRO_TIPO_COMUNICACAO.getMensagem());
    }
  }

  /**
   * Verifica se os dados obrigatorios do destinatario esta preenchido.
   * @param requisicao
   */
  public static void validaDadosDestinatario(final RequisicaoAgendamentoDto requisicao) {
    if (destinatarioEhNull(requisicao)
        || (requisicao.getCelular().isBlank() && requisicao.getEmail().isBlank())) {
      throw new AgendamentoException(ERRO_DESTINATARIO_OBRIGATORIO.getCodigo(),
          ERRO_DESTINATARIO_OBRIGATORIO.getMensagem());
    }
  }

  /**
   * Valida se destinatario esta preenchido
   * @param requisicao
   * @return boolean
   */
  private static boolean destinatarioEhNull(final RequisicaoAgendamentoDto requisicao) {
    return isNull(requisicao.getCelular()) && isNull(requisicao.getEmail());
  }
}
