package br.com.higino.app_agendamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.higino.app_agendamento.model.Desenvolvedor;

@Repository
public interface DesenvolvedorRepository extends JpaRepository<Desenvolvedor, Long> {
    Desenvolvedor findByUsuario(String usuario);
}
