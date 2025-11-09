package br.com.higino.app_agendamento.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteAuthController {

    @GetMapping("/api/teste-auth")
    public String testarAuth(Authentication authentication) {
        if (authentication == null) {
            return "❌ Usuário NÃO autenticado!";
        } else {
            return "✅ Usuário autenticado: " + authentication.getName();
        }
    }
}
