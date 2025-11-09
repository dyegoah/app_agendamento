package br.com.higino.app_agendamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.higino.app_agendamento.model.AgendamentoDespesa;
import br.com.higino.app_agendamento.repository.AgendamentoDespesaRepository;

@RestController
@RequestMapping("/api/agendamento-despesa")

public class AgendamentoDespesaController {

    @Autowired
    private AgendamentoDespesaRepository repository;

    // ===============================================================
    // ✅ SALVAR DESPESA MENSAL
    // ===============================================================
    @PostMapping("/mensal")
    public ResponseEntity<?> salvarDespesaMensal(@RequestBody AgendamentoDespesa despesa) {
        try {
            System.out.println("📩 Recebendo despesa mensal: " + despesa.getItem() + " - R$" + despesa.getValorTotal());

            // garante que será classificada como mensal
            despesa.setTipoDespesa("mensal");

            // persiste no banco
            AgendamentoDespesa salvo = repository.save(despesa);

            System.out.println("💾 Despesa salva com ID: " + salvo.getId());
            return ResponseEntity.ok(salvo);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body("Erro ao salvar despesa mensal: " + e.getMessage());
        }
    }

    // ===============================================================
    // ✅ LISTAR DESPESAS MENSAIS POR LOJA
    // ===============================================================
    @GetMapping("/mensal/{lojaId}")
    public ResponseEntity<List<AgendamentoDespesa>> listarMensais(@PathVariable Long lojaId) {
        List<AgendamentoDespesa> lista = repository.findByLojaIdAndTipoDespesa(lojaId, "mensal");
        System.out.println("📤 Retornando " + lista.size() + " despesas mensais para loja " + lojaId);
        return ResponseEntity.ok(lista);
    }

    // ===============================================================
    // ✅ TESTE DE ROTA
    // ===============================================================
    @GetMapping("/teste")
    public String teste() {
        return "✅ Controller de despesas funcionando!";
    }
    
 // 🔹 SALVAR DESPESA GENÉRICA
    @PostMapping("/{tipo}")
    public ResponseEntity<?> salvarDespesaPorTipo(@PathVariable String tipo, @RequestBody AgendamentoDespesa despesa) {
        try {
            despesa.setTipoDespesa(tipo);
            AgendamentoDespesa salvo = repository.save(despesa);
            return ResponseEntity.ok(salvo);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Erro ao salvar despesa tipo " + tipo + ": " + e.getMessage());
        }
    }

    // 🔹 LISTAR DESPESA GENÉRICA
    @GetMapping("/{tipo}/{lojaId}")
    public ResponseEntity<List<AgendamentoDespesa>> listarPorTipo(
            @PathVariable String tipo, @PathVariable Long lojaId) {
        List<AgendamentoDespesa> lista = repository.findByLojaIdAndTipoDespesa(lojaId, tipo);
        return ResponseEntity.ok(lista);
    }

 // 🔹 Salvar Serviço (tabela "Serviços e Valores por Agendamento")
    @PostMapping("/servico")
    public ResponseEntity<?> salvarServico(@RequestBody AgendamentoDespesa servico) {
        try {
            servico.setTipoDespesa("servico");        // marca o tipo
            servico.setNomeTabela("servicosAgendamento");
            AgendamentoDespesa salvo = repository.save(servico);
            return ResponseEntity.ok(salvo);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body("Erro ao salvar serviço: " + e.getMessage());
        }
    }

    // 🔹 Listar Serviços por loja
    @GetMapping("/servico/{lojaId}")
    public ResponseEntity<List<AgendamentoDespesa>> listarServicos(@PathVariable Long lojaId) {
        // Pode usar por tipo ou por nomeTabela — ambos funcionam:
        // List<AgendamentoDespesa> lista = repository.findByLojaIdAndTipoDespesa(lojaId, "servico");
        List<AgendamentoDespesa> lista = repository.findByNomeTabelaAndLojaId("servicosAgendamento", lojaId);
        return ResponseEntity.ok(lista);
    }

    
}
