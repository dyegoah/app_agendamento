package br.com.higino.app_agendamento.controllers;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.higino.app_agendamento.dto.AdminStatsDTO;
import br.com.higino.app_agendamento.model.Loja;
import br.com.higino.app_agendamento.model.Servico;
import br.com.higino.app_agendamento.model.Usuario;
import br.com.higino.app_agendamento.repository.AgendamentoRepository;
import br.com.higino.app_agendamento.repository.LojaRepository;
import br.com.higino.app_agendamento.repository.ServicoRepository;
import br.com.higino.app_agendamento.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

  @Autowired private LojaRepository lojaRepository;
  @Autowired private UsuarioRepository usuarioRepository;
  @Autowired private ServicoRepository servicoRepository;
  @Autowired private AgendamentoRepository agendamentoRepository;

  @GetMapping("/lojas")
  public List<Loja> listarLojas() {
    return lojaRepository.findAll();
  }

  @PostMapping("/lojas")
  public ResponseEntity<Loja> criarLoja(@RequestBody Loja nova) {
    Loja loja = lojaRepository.save(nova);

    Usuario user = new Usuario();
    user.setEmail(loja.getEmail());
    user.setSenha("123456");
    user.setPapel("LOJA");
    user.setLoja(loja);
    usuarioRepository.save(user);

    List<Servico> base = Arrays.asList(
        new Servico("Maquiagem básica", 80.0, loja),
        new Servico("Maquiagem de festa", 120.0, loja),
        new Servico("Maquiagem noiva", 200.0, loja)
    );
    servicoRepository.saveAll(base);

    return ResponseEntity.ok(loja);
  }

  @GetMapping("/stats")
  public AdminStatsDTO stats() {
    long lojas = lojaRepository.count();
    long agends = agendamentoRepository.count();
    BigDecimal receita = agendamentoRepository.receitaGeral();
    return new AdminStatsDTO(lojas, agends, receita);
  }
}
	