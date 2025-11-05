package br.com.higino.app_agendamento.controllers;

import java.io.IOException;
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
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/lojas") // ✅ rota completa usada pelo front
@CrossOrigin(origins = "*")
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

    // =====================================================
    // 🔹 LOGIN DO CLIENTE (LOJA)
    // =====================================================
    @PostMapping("/login")
    public ResponseEntity<?> loginLoja(@RequestBody Map<String, String> credenciais, HttpServletRequest request) {
        try {
            String email = credenciais.get("email");
            String senha = credenciais.get("senha");

            // Busca loja por email (precisa existir em LojaRepository)
            Optional<Loja> lojaOpt = repository.findByEmailIgnoreCase(email);
            if (lojaOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha incorretos.");
            }

            Loja loja = lojaOpt.get();

            // Validação de senha simples (ideal: usar BCrypt futuramente)
            if (!loja.getSenha().equals(senha)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha incorretos.");
            }

            // Verifica status da loja
            if ("INADIMPLENTE".equalsIgnoreCase(loja.getStatus())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Conta em atraso. Verifique com o suporte.");
            }

            // Cria sessão
            HttpSession session = request.getSession(true);
            session.setAttribute("usuarioLogado", loja.getId());
            session.setAttribute("role", "CLIENTE");
            session.setMaxInactiveInterval(60 * 60 * 8); // 8 horas

            // Retorna dados essenciais para o front
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

    // =====================================================
    // 🔹 LOGIN DO DESENVOLVEDOR
    // =====================================================
    @PostMapping("/dev-login")
    public ResponseEntity<?> loginDev(@RequestBody Map<String, String> cred, HttpServletRequest request) {
        try {
            String usuario = cred.get("usuario");
            String senha = cred.get("senha");

            // Credenciais padrão do desenvolvedor
            if ("dyegoah".equals(usuario) && "dy142434".equals(senha)) {
                HttpSession session = request.getSession(true);
                session.setAttribute("usuarioLogado", "DEV_USER");
                session.setAttribute("role", "DEV");
                session.setMaxInactiveInterval(60 * 60 * 8); // 8 horas
                return ResponseEntity.ok(Map.of("ok", true));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha incorretos!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro no login do desenvolvedor.");
        }
    }

    // =====================================================
    // 🔹 LOGOUT DO DESENVOLVEDOR
    // =====================================================
    @GetMapping("/logout-dev")
    public void logoutDev(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession s = request.getSession(false);
        if (s != null) s.invalidate();
        response.sendRedirect("/loginDesenvolvedor.html");
    }

}
