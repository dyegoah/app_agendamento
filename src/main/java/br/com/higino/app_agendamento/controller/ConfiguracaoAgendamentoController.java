package br.com.higino.app_agendamento.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.higino.app_agendamento.model.ConfiguracaoAgendamento;
import br.com.higino.app_agendamento.model.RequisicaoAgendamento;
import br.com.higino.app_agendamento.repository.ConfiguracaoAgendamentoRepository;
import br.com.higino.app_agendamento.repository.RequisicaoAgendamentoRepository;

@RestController
@RequestMapping("/api/configuracao-agendamento")
@CrossOrigin(origins = "*") // ✅ libera acesso de qualquer origem
public class ConfiguracaoAgendamentoController {

    @Autowired
    private ConfiguracaoAgendamentoRepository repo;
    @Autowired
    private RequisicaoAgendamentoRepository requisicaoRepo;


    // ✅ Salva todas as configurações enviadas
    @PostMapping("/salvar")
    public ResponseEntity<?> salvarRequisicao(@RequestBody List<ConfiguracaoAgendamento> configuracoes,
                                              @RequestParam(required = false, defaultValue = "PERSONALIZADO") String tipo) {
        if (configuracoes == null || configuracoes.isEmpty()) {
            return ResponseEntity.badRequest().body("Lista de configurações vazia.");
        }

        RequisicaoAgendamento req = new RequisicaoAgendamento();
        req.setTipo(tipo);

        configuracoes.forEach(c -> c.setRequisicao(req));
        req.setConfiguracoes(configuracoes);

        requisicaoRepo.save(req);
        return ResponseEntity.ok("Configurações salvas com ID da requisição: " + req.getId());
    }


    // ✅ Lista todas (para testar)
    @GetMapping("/todas")
    public List<ConfiguracaoAgendamento> listar() {
        return repo.findAll();
    }

    // ✅ Endpoint público (para a página agendamentoServico)
    @GetMapping("/ativas")
    public List<ConfiguracaoAgendamento> listarAtivas() {
        return repo.findByAtivoTrue();
    }
    
    @DeleteMapping("/limpar-antigas")
    public ResponseEntity<?> limparAntigas() {
        LocalDate limite = LocalDate.now();
        List<RequisicaoAgendamento> antigas = requisicaoRepo.findAll().stream()
            .filter(r -> r.getDataCriacao().isBefore(LocalDateTime.now().minusDays(7)))
            .toList();

        if (antigas.isEmpty()) return ResponseEntity.ok("Nenhuma requisição antiga encontrada.");

        requisicaoRepo.deleteAll(antigas);
        return ResponseEntity.ok("Requisições antigas removidas: " + antigas.size());
    }
    
 // 🔓 Endpoint público para o site (sem autenticação)
    @GetMapping("/publicas")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<ConfiguracaoAgendamento>> listarPublicas() {
        try {
            List<ConfiguracaoAgendamento> lista = repo.findByAtivoTrue();
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/apagar-tudo")
    public ResponseEntity<?> apagarTudo() {
        try {
            repo.deleteAll();
            requisicaoRepo.deleteAll();
            return ResponseEntity.ok("Todas as configurações foram removidas com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Erro ao tentar apagar todas as configurações.");
        }
    }


}
