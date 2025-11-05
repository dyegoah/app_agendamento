package br.com.higino.app_agendamento.controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.nio.file.Files;

@Controller
public class PublicPageController {

    // 🔓 Endpoint público: /agendamento-publico
    @GetMapping(value = "/agendamento-publico", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<byte[]> getPublicAgendamentoPage() throws IOException {
        var resource = new ClassPathResource("static/painel/agendamentoServico.html");
        byte[] content = Files.readAllBytes(resource.getFile().toPath());
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(content);
    }
}
