package br.com.higino.app_agendamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.higino.app_agendamento.model.ConfiguracaoAgendamento;
import java.util.List;

@Repository
public interface ConfiguracaoAgendamentoRepository extends JpaRepository<ConfiguracaoAgendamento, Long> {
    List<ConfiguracaoAgendamento> findByAtivoTrue();
}
