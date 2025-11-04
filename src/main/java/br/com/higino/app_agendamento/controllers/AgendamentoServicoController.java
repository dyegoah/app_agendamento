package br.com.higino.app_agendamento.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.higino.app_agendamento.model.AgendamentoServico;
import br.com.higino.app_agendamento.repository.AgendamentoServicoRepository;

@RestController
@RequestMapping("/api/agendamento-servico")
@CrossOrigin(origins = "*")
public class AgendamentoServicoController {

    @Autowired
    private AgendamentoServicoRepository repository;

    // 🔹 POST - Salvar novo agendamento
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody AgendamentoServico agendamento) {
        try {
            agendamento.setStatus("Aguardando");
            AgendamentoServico salvo = repository.save(agendamento);
            return ResponseEntity.ok(salvo);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Erro ao salvar agendamento: " + e.getMessage());
        }
    }

    // 🔹 GET - Listar todos (para painelGerencia)
    @GetMapping
    public ResponseEntity<List<AgendamentoServico>> listarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }

    // 🔹 GET - Listar por status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<AgendamentoServico>> listarPorStatus(@PathVariable String status) {
        return ResponseEntity.ok(repository.findByStatusIgnoreCase(status));
    }

    // ✅ PUT - Atualizar status (Atendido / Cancelado)
    @PutMapping("/{id}/status")
    public ResponseEntity<AgendamentoServico> atualizarStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {

        return repository.findById(id).map(ag -> {
            ag.setStatus(body.get("status"));
            AgendamentoServico atualizado = repository.save(ag);
            return ResponseEntity.ok(atualizado);
        }).orElse(ResponseEntity.notFound().build());
    }
    
   

        @Autowired
        private AgendamentoServicoRepository agendamentoRepository;

        // ... (outros métodos que já existem aqui)

        // ✅ Atualizar somente o campo "servico" de um agendamento existente
        @PutMapping("/{id}/servico")
        public ResponseEntity<?> atualizarServico(@PathVariable Long id, @RequestBody Map<String, String> payload) {
            String novoServico = payload.get("servico");
            return agendamentoRepository.findById(id)
                .map(ag -> {
                    ag.setServico(novoServico);
                    agendamentoRepository.save(ag);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
        }
    }

    

