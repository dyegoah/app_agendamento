package br.com.higino.app_agendamento.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DevViewController {

    @GetMapping("/dev/login")
    public String devLogin() {
        // entrega o HTML físico de /static/dev_login.html
        return "forward:/dev_login.html";
    }

    @GetMapping("/dev/painel")
    public String devPainel(HttpSession session) {
        Object user = (session != null) ? session.getAttribute("usuarioLogado") : null;
        if (user == null) {
            // sem sessão → volta pro login
            return "redirect:/dev/login";
        }
        // sessão ok → entrega o HTML do painel
        return "forward:/dev_painel.html";
    }
}
