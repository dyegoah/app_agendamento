package br.com.higino.app_agendamento.repository;



import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.higino.app_agendamento.model.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
  long countByLojaId(Long lojaId);

  long countByLojaIdAndDataHoraBetween(Long lojaId, LocalDateTime ini, LocalDateTime fim);

  Optional<Agendamento> findFirstByLojaIdAndDataHoraAfterOrderByDataHoraAsc(Long lojaId, LocalDateTime depois);

  @Query("""
     select coalesce(sum(a.valor), 0) 
     from Agendamento a
     where a.status = 'PAGO'
       and a.loja.id = :lojaId
       and a.dataHora between :ini and :fim
  """)
  BigDecimal receitaMesLoja(@Param("lojaId") Long lojaId,
                            @Param("ini") LocalDateTime ini,
                            @Param("fim") LocalDateTime fim);

  @Query("""
     select coalesce(sum(a.valor), 0)
     from Agendamento a
     where a.status = 'PAGO'
  """)
  BigDecimal receitaGeral();

  @Query("""
     select a.servico.nome
     from Agendamento a
     where a.loja.id = :lojaId
     group by a.servico.nome
     order by count(a.id) desc
  """)
  java.util.List<String> topServico(@Param("lojaId") Long lojaId);
}
