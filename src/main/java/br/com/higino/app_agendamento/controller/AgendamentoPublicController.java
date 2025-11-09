package br.com.higino.app_agendamento.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.higino.app_agendamento.model.Agendamento;
import br.com.higino.app_agendamento.repository.AgendamentoRepository;

@RestController
@RequestMapping("/api/publico/agendamentos")
@CrossOrigin(origins = "*")
public class AgendamentoPublicController {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @PostMapping
    public ResponseEntity<Agendamento> criar(@RequestBody Agendamento agendamento) {
       
        Agendamento salvo = agendamentoRepository.save(agendamento);
        return ResponseEntity.ok(salvo);
    }
}
