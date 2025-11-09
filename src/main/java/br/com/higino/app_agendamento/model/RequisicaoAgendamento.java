package br.com.higino.app_agendamento.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class RequisicaoAgendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo; // "SEMANA", "MES", "ANO", etc.
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @OneToMany(mappedBy = "requisicao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConfiguracaoAgendamento> configuracoes;

    // Getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public List<ConfiguracaoAgendamento> getConfiguracoes() { return configuracoes; }
    public void setConfiguracoes(List<ConfiguracaoAgendamento> configuracoes) { this.configuracoes = configuracoes; }
}
