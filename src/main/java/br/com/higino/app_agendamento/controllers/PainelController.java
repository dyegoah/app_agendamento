package br.com.higino.app_agendamento.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class PainelController {

    // 🔹 Rota para acessar diretamente o painel (via navegador ou redirecionamento)
	@GetMapping("/painelGerencia.html")
	public String abrirPainelGerencia() {
	    return "redirect:/painelGerencia.html";
	}

    // 🔹 Rota alternativa amigável (opcional)
    @GetMapping("/painel/gerencia")
    public String redirecionarParaPainel() {
        return "redirect:/painelGerencia.html";
    }
    
    // 🔹 Rota segura para sair do sistema
    @GetMapping("/sair")
    public String sair(HttpSession session) {
        session.invalidate();
        return "redirect:/app_agendamento/loginAcesso.html";
    }
    
    @GetMapping("/painel/agendamentoServico")
    public String abrirAgendamentoServico() {
        return "redirect:/painel/agendamentoServico.html";
    }

    @GetMapping("/painel/configAgendamento")
    public String abrirConfigAgendamento() {
        return "redirect:/painel/configAgendamento.html";
    }

    @GetMapping("/painel/agendamentoDespesa")
    public String abrirAgendamentoDespesa() {
        return "redirect:/painel/agendamentoDespesa.html";
    }

    @GetMapping("/painel/relatorioAgendamento")
    public String abrirRelatorioAgendamento() {
        return "redirect:/painel/relatorioAgendamento.html";
    }

    @GetMapping("/painel/agendamentoCardapio")
    public String abrirAgendamentoCardapio() {
        return "redirect:/painel/agendamentoCardapio.html";
    }

    @GetMapping("/painel/configServico")
    public String abrirConfigServico() {
        return "redirect:/painel/configServico.html";
    }

}
