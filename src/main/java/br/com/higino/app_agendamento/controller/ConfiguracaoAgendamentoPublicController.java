package br.com.higino.app_agendamento.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import br.com.higino.app_agendamento.model.ConfiguracaoAgendamento;
import br.com.higino.app_agendamento.repository.ConfiguracaoAgendamentoRepository;

@RestController
@RequestMapping("/api/publico/configuracoes")
@CrossOrigin(origins = "*")
public class ConfiguracaoAgendamentoPublicController {

    @Autowired
    private ConfiguracaoAgendamentoRepository repo;

    @GetMapping("/ativas")
    public List<ConfiguracaoAgendamento> listarAtivas() {
        return repo.findAll(); // ou repo.findByAtivoTrue();
    }
}
