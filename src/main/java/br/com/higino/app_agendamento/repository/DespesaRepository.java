package br.com.higino.app_agendamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.higino.app_agendamento.model.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {
}
