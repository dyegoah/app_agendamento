package br.com.higino.app_agendamento.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublicPageController {

	@GetMapping("/agendamento-publico")
	public String redirecionar() {
		 return "redirect:/agendamentoServico.html";
	}

}
