package br.com.higino.app_agendamento.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.higino.app_agendamento.model.ConfiguracaoAgendamento;

@Repository
public interface ConfiguracaoAgendamentoRepository extends JpaRepository<ConfiguracaoAgendamento, Long> {
    Optional<ConfiguracaoAgendamento> findByData(LocalDate data);
}
