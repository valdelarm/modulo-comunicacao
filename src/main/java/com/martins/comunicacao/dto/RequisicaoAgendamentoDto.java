package com.martins.comunicacao.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.martins.comunicacao.enumeration.StatusComunicacao;
import com.martins.comunicacao.enumeration.TipoComunicacao;
import com.martins.comunicacao.model.Agendamento;
import java.time.LocalDateTime;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequisicaoAgendamentoDto {

  @NotNull
  private LocalDateTime dataHoraEnvio;
  @Email
  private String email;
  private String celular;
  @NotBlank
  private String mensagem;
  @NotNull
  private TipoComunicacao tipoComunicacao;
  @NotBlank
  private String nome;

  public Agendamento paraEntidade() {
    Agendamento entidade = new Agendamento();
    entidade.setEmail(this.email);
    entidade.setNumeroCelular(this.celular);
    entidade.setMensagem(this.mensagem);
    entidade.setHoraEnvio(this.dataHoraEnvio);
    entidade.setNomeDestinatario(this.nome);
    entidade.setStatus(StatusComunicacao.PENDENTE);
    entidade.setTipoComunicacao(this.tipoComunicacao);

    return entidade;
  }

  public LocalDateTime getDataHoraEnvio() {
    return dataHoraEnvio;
  }

  public void setDataHoraEnvio(LocalDateTime dataHoraEnvio) {
    this.dataHoraEnvio = dataHoraEnvio;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCelular() {
    return celular;
  }

  public void setCelular(String celular) {
    this.celular = celular;
  }

  public String getMensagem() {
    return mensagem;
  }

  public void setMensagem(String mensagem) {
    this.mensagem = mensagem;
  }

  public TipoComunicacao getTipoComunicacao() {
    return tipoComunicacao;
  }

  public void setTipoComunicacao(TipoComunicacao tipoComunicacao) {
    this.tipoComunicacao = tipoComunicacao;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }
}
