package com.martins.comunicacao.controller;

import com.martins.comunicacao.dto.RequisicaoAgendamentoDto;
import com.martins.comunicacao.dto.RespostaStatusAgendamentoDto;
import com.martins.comunicacao.model.Agendamento;
import com.martins.comunicacao.service.AgendamentoService;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.net.URI;
import java.net.URISyntaxException;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/agendamentos")
@Api("Enpoint para agendamentos de comunicacao com cliente")
@Timed("agendamento")
public class AgendamentoController {

  private final AgendamentoService agendamentoService;

  public AgendamentoController(
      AgendamentoService agendamentoService) {
    this.agendamentoService = agendamentoService;
  }

  @PostMapping
  @ApiOperation(value = "Cria  um novo agendamento para comunicação com o cliente", response = Agendamento.class)
  @Timed(value = "agendamento.criarTimer", description = "Verifica quanto tempo demora para criar um agendamento", longTask = true)
  @Counted(value = "agendamento.criarCounter", description = "Verifica quantas vezes o recurso eh chamado")
  public ResponseEntity criarAgendamento(@RequestBody @Valid RequisicaoAgendamentoDto requisicao)
      throws URISyntaxException {
      Agendamento resposta = agendamentoService.criarAgendamento(requisicao);
      return ResponseEntity.created(new URI("/agendamentos/" + resposta.getId())).body(resposta);
  }

  @GetMapping("/{id}")
  @ApiOperation(value = "Recupera agendamento", response = RespostaStatusAgendamentoDto.class)
  @Timed(value = "agendamento.recuperarTimer", description = "Verifica quanto tempo demora para recuperar um agendamento", longTask = true)
  @Counted(value = "agendamento.recuperarCounter", description = "Verifica quantas vezes o recurso eh chamado")
  public ResponseEntity recuperaAgendamento(@PathVariable("id") Long agendamentoId) {
      RespostaStatusAgendamentoDto resposta = agendamentoService.recuperarAgendamento(agendamentoId);
      return ResponseEntity.ok(resposta);
  }

  @DeleteMapping("/{id}")
  @ApiOperation(value = "Remove agendamento")
  @Timed(value = "agendamento.removerTimer", description = "Verifica quanto tempo demora para remover um agendamento", longTask = true)
  @Counted(value = "agendamento.removerCounter", description = "Verifica quantas vezes o recurso eh chamado")
  public ResponseEntity removeAgendamento(@PathVariable("id") Long agendamentoId) {
    agendamentoService.removeAgendamento(agendamentoId);
    return ResponseEntity.noContent().build();
  }
}
