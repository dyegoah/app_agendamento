package br.com.higino.app_agendamento.repository;

import br.com.higino.app_agendamento.model.Loja;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LojaRepository extends JpaRepository<Loja, Long> {
    Optional<Loja> findByEmail(String email);
}
