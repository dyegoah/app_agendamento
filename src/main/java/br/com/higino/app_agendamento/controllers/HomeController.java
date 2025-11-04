package br.com.higino.app_agendamento.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String redirecionarParaLogin() {
        return "redirect:/loginAcesso.html";
    }
    
    @Controller
    public class DashboardController {
        
    }
}
