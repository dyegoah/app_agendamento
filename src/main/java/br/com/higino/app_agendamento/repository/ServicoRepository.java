package br.com.higino.app_agendamento.repository;

import br.com.higino.app_agendamento.model.Servico;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

    // 🔹 Busca o serviço pelo nome (campo existente no seu model)
    @Query("""
           SELECT s FROM Servico s
           WHERE LOWER(s.nome) = LOWER(:nome)
           """)
    Optional<Servico> findAnyByNomeOuItem(@Param("nome") String nome);

}
