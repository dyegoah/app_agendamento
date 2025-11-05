package br.com.higino.app_agendamento;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // Libera recursos estáticos (CSS, JS, imagens, favicon etc.)
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers(
                    "/", 
                    "/index.html",
                    "/loginAcesso.html", // Público para login
                    "/loginDesenvolvedor.html", // Público para login
                    "/agendamentoServico.html", // ✅ APENAS ESTE É PÚBLICO
                    // Recursos estáticos
                    "/css/**",
                    "/js/**",
                    "/images/**",
                    "/favicon.ico",
                    "/static/**"
                ).permitAll()
                // APIs públicas (apenas agendamento-servico)
                .requestMatchers(
                    "/api/agendamento-servico/**", // ✅ API PÚBLICA para agendamentos
                    "/api/lojas/**", 
                    "/api/public/**"
                ).permitAll()
                // 🔒 DEMIAS ROTAS PRECISAM DE AUTENTICAÇÃO
                .requestMatchers(
                    "/painel/**", // Painel administrativo PROTEGIDO
                    "/cadastroLojas.html",
                    "/painelGerencia.html", 
                    "/agendamentoDespesa.html",
                    "/configAgendamento.html",
                    "/relatorioAgendamento.html"
                ).authenticated()
                // Demais requisições
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/loginAcesso.html") // Página de login personalizada
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/painel/painelGerencia.html", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/loginAcesso.html")
                .permitAll()
            );

        return http.build();
    }
}