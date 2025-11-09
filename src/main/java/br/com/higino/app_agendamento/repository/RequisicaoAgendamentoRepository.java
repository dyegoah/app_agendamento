package br.com.higino.app_agendamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.higino.app_agendamento.model.RequisicaoAgendamento;

public interface RequisicaoAgendamentoRepository extends JpaRepository<RequisicaoAgendamento, Long> {}
