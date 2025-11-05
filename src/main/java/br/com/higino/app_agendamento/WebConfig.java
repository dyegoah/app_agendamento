package br.com.higino.app_agendamento;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
        registry.addViewController("/cadastroLojas").setViewName("forward:/cadastroLojas.html");
        registry.addViewController("/loginDesenvolvedor").setViewName("forward:/loginDesenvolvedor.html");
        registry.addViewController("/painelDesenvolvedor").setViewName("forward:/painelDesenvolvedor.html");
    }

    // 🔐 Proteção de acesso ao painel do desenvolvedor
    @Bean
    public FilterRegistrationBean<Filter> devSecurityFilter() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();

        registration.setFilter(new Filter() {
            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                    throws java.io.IOException, ServletException {

                HttpServletRequest req = (HttpServletRequest) request;
                HttpServletResponse res = (HttpServletResponse) response;
                HttpSession session = req.getSession(false);

                String uri = req.getRequestURI();

                // Protege somente o painel do desenvolvedor
                if (uri.contains("/painelDesenvolvedor")) {
                    if (session == null || session.getAttribute("devAutenticado") == null) {
                        res.sendRedirect(req.getContextPath() + "/loginDesenvolvedor");
                        return;
                    }
                }

                // Logout: remove sessão
                if (uri.contains("/logout-dev")) {
                    if (session != null) session.invalidate();
                    res.sendRedirect(req.getContextPath() + "/loginDesenvolvedor");
                    return;
                }

                chain.doFilter(request, response);
            }
        });

        registration.addUrlPatterns("/*");
        registration.setName("devSecurityFilter");
        registration.setOrder(1);

        return registration;
    }
}
