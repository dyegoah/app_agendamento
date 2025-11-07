package br.com.higino.app_agendamento;

import java.io.IOException;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class WebConfig {

	@Bean
	public FilterRegistrationBean<Filter> corsHeaders() {
	    FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
	    registrationBean.setFilter(new Filter() {
	        @Override
	        public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
	                throws IOException, ServletException {
	            HttpServletResponse response = (HttpServletResponse) res;
	            HttpServletRequest request = (HttpServletRequest) req;

	            String origin = request.getHeader("Origin");
	            if (origin != null && !origin.isEmpty()) {
	                response.setHeader("Access-Control-Allow-Origin", origin); // ✅ dinâmico
	            }

	            response.setHeader("Access-Control-Allow-Credentials", "true");
	            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
	            response.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization");

	            if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
	                response.setStatus(HttpServletResponse.SC_OK);
	            } else {
	                chain.doFilter(req, res);
	            }
	        }
	    });
	    registrationBean.setOrder(0);
	    return registrationBean;
	}

}
