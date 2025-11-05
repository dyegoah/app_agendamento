package br.com.higino.app_agendamento;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * 🔓 Cadeia de segurança exclusiva para a página pública
     * Isola /painel/agendamentoServico.html e a API associada.
     */
    @Bean
    @Order(1)
    public SecurityFilterChain publicChain(HttpSecurity http) throws Exception {

        http
            .securityMatcher(
                new AntPathRequestMatcher("/painel/agendamentoServico.html"),
                new AntPathRequestMatcher("/api/agendamento-servico/**")
            )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/painel/agendamentoServico.html").permitAll()
                .requestMatchers("/api/agendamento-servico/**").permitAll()
                .anyRequest().permitAll()
            )
            .csrf(csrf -> csrf.disable()); // desabilita CSRF para permitir fetch() público

        return http.build();
    }

    /**
     * 🔒 Cadeia principal de segurança — protege o restante do sistema.
     */
    @Bean
    @Order(2)
    public SecurityFilterChain mainChain(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/login",
                    "/css/**",
                    "/js/**",
                    "/img/**",
                    "/uploads/**"
                ).permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(login -> login
                .loginPage("/login")
                .defaultSuccessUrl("/painel", true)
                .permitAll()
            )
            .logout(logout -> logout.permitAll());

        return http.build();
    }

    /**
     * 🧩 Ignora completamente o arquivo HTML estático.
     * Isso faz com que o Spring Security nem tente interceptar esse arquivo.
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/painel/agendamentoServico.html");
    }
}
