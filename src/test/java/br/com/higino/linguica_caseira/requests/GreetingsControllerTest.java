package br.com.higino.linguica_caseira.requests;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsControllerTest {

    @GetMapping("/api/greetings")
    public String greetings() {
        return "Bem-vindo à aplicação de linguiça caseira!";
    }
}


