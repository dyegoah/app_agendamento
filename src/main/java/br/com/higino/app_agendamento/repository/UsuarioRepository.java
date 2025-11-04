package br.com.higino.app_agendamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.higino.app_agendamento.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}
