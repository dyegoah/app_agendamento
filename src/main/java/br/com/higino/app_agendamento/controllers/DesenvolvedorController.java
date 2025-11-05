package br.com.higino.app_agendamento.controllers;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
public class DesenvolvedorController {

    // =====================================================
    // 🔹 LOGIN DO DESENVOLVEDOR
    // =====================================================
    @PostMapping("/api/dev-login")
    public ResponseEntity<?> loginDev(@RequestBody Map<String, String> cred, HttpServletRequest request) {
        String usuario = cred.get("usuario");
        String senha = cred.get("senha");

        // Credenciais fixas do desenvolvedor
        if ("admin".equals(usuario) && "dev123".equals(senha)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("usuarioLogado", "DEV_USER");
            session.setAttribute("role", "DEV");
            session.setMaxInactiveInterval(60 * 60 * 8); // 8 horas

            return ResponseEntity.ok(Map.of("ok", true));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha incorretos!");
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
