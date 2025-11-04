package br.com.higino.app_agendamento.security;

import java.io.IOException;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
	@Order(1)
	public class AuthFilter implements Filter {

	  @Override
	  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
	      throws IOException, ServletException {
	    HttpServletRequest request = (HttpServletRequest) req;
	    HttpServletResponse response = (HttpServletResponse) res;
	    String path = request.getRequestURI();

	    // Só vigie os HTML do painel
	    boolean painelCliente = path.startsWith("/app_agendamento/painel/");
	    boolean painelDev     = path.endsWith("/app_agendamento/painelDesenvolvedor.html");

	    if (!(painelCliente || painelDev)) {
	      chain.doFilter(req, res);
	      return;
	    }

	    HttpSession session = request.getSession(false);
	    if (session == null || session.getAttribute("usuarioLogado") == null) {
	      response.sendRedirect("/app_agendamento/loginAcesso.html");
	      return;
	    }

	    // Se for painel do DEV, além de logado, precisa ter atributo ROLE_DEV
	    if (painelDev) {
	      Object role = session.getAttribute("role");
	      if (role == null || !"DEV".equals(role.toString())) {
	        response.sendRedirect("/app_agendamento/loginDesenvolvedor.html");
	        return;
	      }
	    }

	    chain.doFilter(req, res);
	  }
	

}
