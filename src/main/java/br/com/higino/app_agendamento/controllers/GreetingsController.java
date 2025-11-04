package br.com.higino.app_agendamento.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {

    @GetMapping("/api/greetings")
    public String greetings() {
        return "Bem-vindo à aplicação de DH soluções Tecnológicas!";
    }
}


