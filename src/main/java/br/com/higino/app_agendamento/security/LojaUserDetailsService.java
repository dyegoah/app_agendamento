package br.com.higino.app_agendamento.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.higino.app_agendamento.model.Loja;
import br.com.higino.app_agendamento.repository.LojaRepository;

public class LojaUserDetailsService implements UserDetailsService {

    private final LojaRepository lojaRepository;
    private final PasswordEncoder passwordEncoder;

    public LojaUserDetailsService(LojaRepository lojaRepository, PasswordEncoder passwordEncoder) {
        this.lojaRepository = lojaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Loja loja = lojaRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Loja não encontrada: " + email));

        return User.withUsername(loja.getEmail())
                .password(loja.getSenha())
                .roles("USER")
                .build();
    }
}
