package br.com.higino.app_agendamento.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.higino.app_agendamento.model.AgendamentoServico;

@Repository
public interface AgendamentoServicoRepository extends JpaRepository<AgendamentoServico, Long> {
    List<AgendamentoServico> findByStatusIgnoreCase(String status);
}
