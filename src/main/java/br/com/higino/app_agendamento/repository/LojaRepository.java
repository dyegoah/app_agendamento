package br.com.higino.app_agendamento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.higino.app_agendamento.model.Loja;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Long> {
    Loja findByNomeLoja(String nomeLoja);

    Optional<Loja> findTopByEmailIgnoreCaseOrderByIdDesc(String email);
    Optional<Loja> findByEmailIgnoreCase(String email);
    
   
}
