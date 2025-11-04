package br.com.higino.app_agendamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.higino.app_agendamento.model.AgendamentoDespesa;

@Repository
public interface AgendamentoDespesaRepository extends JpaRepository<AgendamentoDespesa, Long> {

    // Correto: ignorar case apenas em campos String
    List<AgendamentoDespesa> findByTipoDespesaIgnoreCase(String tipoDespesa);

    // 🔹 Aqui, sem IgnoreCase porque lojaId é Long
    List<AgendamentoDespesa> findByTipoDespesaAndLojaId(String tipoDespesa, Long lojaId);

    // 🔹 Também sem IgnoreCase, pois lojaId é numérico
    List<AgendamentoDespesa> findByNomeTabelaAndLojaId(String nomeTabela, Long lojaId);
    
    List<AgendamentoDespesa> findByLojaId(Long lojaId);
    
    List<AgendamentoDespesa> findByLojaIdAndTipoDespesa(Long lojaId, String tipoDespesa);
  
}
