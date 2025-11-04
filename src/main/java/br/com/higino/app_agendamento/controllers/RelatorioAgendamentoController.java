package br.com.higino.app_agendamento.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.higino.app_agendamento.model.AgendamentoServico;
import br.com.higino.app_agendamento.repository.AgendamentoServicoRepository;

@RestController
@RequestMapping("/api/relatorio-agendamento")
@CrossOrigin(origins = "*")
public class RelatorioAgendamentoController {

    @Autowired
    private AgendamentoServicoRepository repository;

    // 🔹 Retorna todos os agendamentos
    @GetMapping
    public List<AgendamentoServico> listarAgendamentos() {
        return repository.findAll();
    }

    // 🔹 Busca por data específica (opcional)
    @GetMapping("/data/{data}")
    public List<AgendamentoServico> buscarPorData(@PathVariable String data) {
        return repository.findByData(LocalDate.parse(data));
    }
}
