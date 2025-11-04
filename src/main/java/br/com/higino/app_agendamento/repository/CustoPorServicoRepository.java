package br.com.higino.app_agendamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.higino.app_agendamento.model.CustoPorServico;

public interface CustoPorServicoRepository extends JpaRepository<CustoPorServico, Long> {
    List<CustoPorServico> findByLojaId(Long lojaId);
}
