package br.com.higino.app_agendamento.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Controlador responsável pela autenticação e sessão do painel do desenvolvedor.
 * Compatível com o SecurityConfig atual.
 */
@RestController
 // ✅ necessário p/ manter sessão no navegador
public class DesenvolvedorController {

    // =====================================================
    // 🔹 LOGIN DO DESENVOLVEDOR
    // =====================================================
    @PostMapping("/api/dev-login")
    public ResponseEntity<?> loginDev(@RequestBody Map<String, String> cred, HttpServletRequest request) {
        String usuario = cred.get("usuario");
        String senha = cred.get("senha");

        // ✅ Credenciais fixas do desenvolvedor
        boolean autenticado = false;

        if (usuario != null && senha != null) {
            if (usuario.trim().equalsIgnoreCase("dyegoah") && senha.trim().equals("dy142434")) {
                autenticado = true;
            } else if (usuario.trim().equalsIgnoreCase("admin") && senha.trim().equals("dev123")) {
                autenticado = true;
            }
        }


        if (autenticado) {
            // Cria ou recupera sessão
            HttpSession session = request.getSession(true);
            session.setAttribute("usuarioLogado", "DEV_USER");
            session.setAttribute("role", "DEV");
            session.setMaxInactiveInterval(60 * 60 * 8); // 8h de sessão

            System.out.println("✅ Desenvolvedor autenticado: " + usuario);
            return ResponseEntity.ok(Map.of(
                    "ok", true,
                    "mensagem", "Login realizado com sucesso!",
                    "usuario", usuario
            ));
        }

        System.out.println("❌ Tentativa de login inválida: " + usuario);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("ok", false, "mensagem", "Usuário ou senha incorretos!"));
    }

    // =====================================================
    // 🔹 VERIFICAR SESSÃO (chamado pelo painel)
    // =====================================================
    @GetMapping("/api/dev-session")
    public ResponseEntity<?> verificarSessao(HttpServletRequest request) {
        HttpSession s = request.getSession(false);
        if (s != null && "DEV_USER".equals(s.getAttribute("usuarioLogado"))) {
            return ResponseEntity.ok(Map.of("autenticado", true));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("autenticado", false));
    }

    // =====================================================
    // 🔹 LOGOUT DO DESENVOLVEDOR
    // =====================================================
    @GetMapping("/logout-dev")
    public void logoutDev(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession s = request.getSession(false);
            if (s != null) s.invalidate();
            System.out.println("🚪 Sessão encerrada pelo desenvolvedor.");
            response.sendRedirect("/loginDesenvolvedor.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
