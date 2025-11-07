package br.com.higino.app_agendamento.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.higino.app_agendamento.model.ConfiguracaoAgendamento;
import br.com.higino.app_agendamento.repository.ConfiguracaoAgendamentoRepository;

@RestController
@RequestMapping("/api/config-agendamento")

public class ConfiguracaoAgendamentoController {

    @Autowired
    private ConfiguracaoAgendamentoRepository repository;

    // 🔹 Listar todas as configurações
    @GetMapping
    public List<ConfiguracaoAgendamento> listarTodas() {
        return repository.findAll();
    }

    // 🔹 Buscar por data
    @GetMapping("/{data}")
    public ConfiguracaoAgendamento buscarPorData(@PathVariable String data) {
        return repository.findByData(LocalDate.parse(data)).orElse(null);
    }

    // 🔹 Salvar ou atualizar uma configuração
    @PostMapping
    public ConfiguracaoAgendamento salvar(@RequestBody ConfiguracaoAgendamento configuracao) {
        return repository.save(configuracao);
    }

    // 🔹 Excluir configuração
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
    
    
    
}
