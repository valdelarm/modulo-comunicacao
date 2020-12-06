package com.martins.comunicacao.repository;

import com.martins.comunicacao.model.Agendamento;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
  Optional<Agendamento> findByEmailOrNumeroCelular(String email, String numeroCelular);
}
