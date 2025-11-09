package br.com.higino.app_agendamento;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // acessar "/" já abre o login
        registry.addViewController("/").setViewName("forward:/loginAcesso.html");
    }
}
