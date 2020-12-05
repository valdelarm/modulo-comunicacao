package com.martins.comunicacao.model;

import com.martins.comunicacao.enumeration.StatusComunicacao;
import com.martins.comunicacao.enumeration.TipoComunicacao;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "agendamento")
public class Agendamento {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "hora_envio", nullable = false)
  private LocalDateTime horaEnvio;

  @Column(name = "mensagem", nullable = false)
  @NotBlank
  private String mensagem;

  @Column(name = "tipo_comunicacao", nullable = false)
  @Enumerated(EnumType.STRING)
  private TipoComunicacao tipoComunicacao;

  @Column(name = "status", nullable = false)
  @Enumerated(EnumType.STRING)
  private StatusComunicacao status;

  @Column(name = "nome_destinatario", nullable = false)
  @NotBlank
  private String nomeDestinatario;

  @Column(name = "email")
  @Email
  private String email;

  @Column(name = "numero_celular")
  private String numeroCelular;

  @Column(name = "hora_criacao")
  private LocalDateTime horaCriacao;

  public Long getId() {
    return id;
  }

  public LocalDateTime getHoraEnvio() {
    return horaEnvio;
  }

  public void setHoraEnvio(LocalDateTime horaEnvio) {
    this.horaEnvio = horaEnvio;
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

  public StatusComunicacao getStatus() {
    return status;
  }

  public void setStatus(StatusComunicacao status) {
    this.status = status;
  }

  public String getNomeDestinatario() {
    return nomeDestinatario;
  }

  public void setNomeDestinatario(String nomeDestinatario) {
    this.nomeDestinatario = nomeDestinatario;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getNumeroCelular() {
    return numeroCelular;
  }

  public void setNumeroCelular(String numeroCelular) {
    this.numeroCelular = numeroCelular;
  }

  public LocalDateTime getHoraCriacao() {
    return horaCriacao;
  }

  @PrePersist
  private void setHoraCriacao() {
    this.horaCriacao = LocalDateTime.now();
  }
}
