package br.com.higino.app_agendamento.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Quando acessar a raiz "/", redireciona para o login estático
    @GetMapping("/")
    public String redirecionarParaLogin() {
        return "redirect:/loginAcesso.html";
    }
}
