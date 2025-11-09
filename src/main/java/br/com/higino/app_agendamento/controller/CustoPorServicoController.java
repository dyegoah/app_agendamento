package br.com.higino.app_agendamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.higino.app_agendamento.model.CustoPorServico;
import br.com.higino.app_agendamento.repository.CustoPorServicoRepository;

@RestController
@RequestMapping("/api/custo-servico")
public class CustoPorServicoController {

    @Autowired
    private CustoPorServicoRepository repo;

    // 🔹 Salvar cálculo
    @PostMapping
    public CustoPorServico salvar(@RequestBody CustoPorServico custo) {
        if (custo.getDataCalculo() == null)
            custo.setDataCalculo(java.time.LocalDate.now());
        return repo.save(custo);
    }

    // 🔹 Buscar todos por loja
    @GetMapping("/{lojaId}")
    public List<CustoPorServico> listarPorLoja(@PathVariable Long lojaId) {
        return repo.findByLojaId(lojaId);
    }

    // 🔹 Excluir um
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        repo.deleteById(id);
    }

    // 🔹 Excluir todos (da loja)
    @DeleteMapping("/loja/{lojaId}")
    public void excluirTodosPorLoja(@PathVariable Long lojaId) {
        List<CustoPorServico> lista = repo.findByLojaId(lojaId);
        repo.deleteAll(lista);
    }
}
