package br.com.higino.app_agendamento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.higino.app_agendamento.model.ServicoConfigurado;
import br.com.higino.app_agendamento.repository.ServicoConfiguradoRepository;

@RestController
@RequestMapping("/api/servicos-configurados")

public class ServicoConfiguradoController {

    @Autowired
    private ServicoConfiguradoRepository repository;

    // 🔹 Listar todos os serviços
    @GetMapping
    public List<ServicoConfigurado> listarTodos() {
        return repository.findAll();
    }

    // 🔹 Salvar novo serviço
    @PostMapping
    public ServicoConfigurado salvar(@RequestBody ServicoConfigurado servico) {
        return repository.save(servico);
    }

    // 🔹 Editar serviço existente
    @PutMapping("/{id}")
    public ServicoConfigurado atualizar(@PathVariable Long id, @RequestBody ServicoConfigurado servicoAtualizado) {
        ServicoConfigurado existente = repository.findById(id).orElseThrow();
        existente.setNome(servicoAtualizado.getNome());
        existente.setCategoria(servicoAtualizado.getCategoria());
        existente.setPreco(servicoAtualizado.getPreco());
        existente.setDescricao(servicoAtualizado.getDescricao());
        existente.setImagemBase64(servicoAtualizado.getImagemBase64());
        existente.setChavePix(servicoAtualizado.getChavePix());
        existente.setWhatsapp(servicoAtualizado.getWhatsapp());
        existente.setValorEntrega(servicoAtualizado.getValorEntrega());
        return repository.save(existente);
    }

    // 🔹 Deletar
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
