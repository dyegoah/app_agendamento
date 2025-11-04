package br.com.higino.app_agendamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.higino.app_agendamento.model.ServicoConfigurado;

@Repository
public interface ServicoConfiguradoRepository extends JpaRepository<ServicoConfigurado, Long> {
}
