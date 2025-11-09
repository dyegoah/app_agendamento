package br.com.higino.app_agendamento.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.higino.app_agendamento.model.Loja;
import br.com.higino.app_agendamento.repository.LojaRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/lojas")

public class LojaController {

    @Autowired
    private LojaRepository repository;

    // 🔹 Cadastrar nova loja
    @PostMapping
    public Loja cadastrar(@RequestBody Loja loja) {
        System.out.println("💾 Recebendo nova loja: " + loja.getNomeLoja());
        return repository.save(loja);
    }

    // 🔹 Listar todas as lojas
    @GetMapping
    public List<Loja> listarTodas() {
        return repository.findAll();
    }
    
    @Autowired
    private LojaRepository lojaRepository;
    @GetMapping("/api/lojas")
    public List<Loja> listarTodasAsLojas() {
        System.out.println("🗄️ Carregando lojas do banco em uso: " + System.getenv("DATABASE_URL"));
        List<Loja> lojas = lojaRepository.findAll();
        System.out.println("✅ Total de lojas retornadas: " + lojas.size());
        return lojas;
    }



    // 🔹 Buscar loja por ID
    @GetMapping("/{id}")
    public Loja buscarPorId(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    // 🔹 Excluir loja
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
    
 // Saber se já existe alguma loja (para decidir o primeiro acesso)
    @GetMapping("/existe")
    public Map<String, Boolean> existeLoja() {
        boolean existe = repository.count() > 0;
        Map<String, Boolean> resp = new HashMap<>();
        resp.put("existe", existe);
        return resp;
    }


    // =====================================================
    // 🔹 LOGIN DO CLIENTE (LOJA) - APENAS ESTE MÉTODO DE LOGIN
    // =====================================================
    @PostMapping("/login")
    public ResponseEntity<?> loginLoja(@RequestBody Map<String, String> credenciais, HttpServletRequest request) {
        try {
            String email = credenciais.get("email");
            String senha = credenciais.get("senha");

            Optional<Loja> lojaOpt = repository.findByEmail(email);
            if (lojaOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha incorretos.");
            }

            Loja loja = lojaOpt.get();

            if (!loja.getSenha().equals(senha)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha incorretos.");
            }

            if ("INADIMPLENTE".equalsIgnoreCase(loja.getStatus())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Conta em atraso. Verifique com o suporte.");
            }

            HttpSession session = request.getSession(true);
            session.setAttribute("usuarioLogado", loja.getId());
            session.setAttribute("role", "CLIENTE");
            session.setMaxInactiveInterval(60 * 60 * 8);

            Map<String, Object> resposta = new HashMap<>();
            resposta.put("id", loja.getId());
            resposta.put("nomeComprador", loja.getNomeComprador());
            resposta.put("nomeLoja", loja.getNomeLoja());
            resposta.put("email", loja.getEmail());
            resposta.put("status", loja.getStatus());

            return ResponseEntity.ok(resposta);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro no servidor ao autenticar.");
        }
    }

    // ❌ NÃO TERÁ MAIS MÉTODO dev-login AQUI
}