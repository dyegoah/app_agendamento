package br.com.higino.app_agendamento.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.higino.app_agendamento.model.Despesa;
import br.com.higino.app_agendamento.repository.DespesaRepository;

@RestController
@RequestMapping("/api/despesas")
public class DespesaController {

    @Autowired
    private DespesaRepository repo;

    @GetMapping
    public List<Despesa> listar() {
        return repo.findAll();
    }
}
