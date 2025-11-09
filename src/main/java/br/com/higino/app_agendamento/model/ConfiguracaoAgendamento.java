package br.com.higino.app_agendamento.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "configuracao_agendamento")
public class ConfiguracaoAgendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;
    private String horario;
    private boolean ativo;
    private String servico;

    // 🔹 NOVO RELACIONAMENTO com a requisição principal
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requisicao_id")
    private RequisicaoAgendamento requisicao;

    // =========================
    // Getters e Setters
    // =========================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public RequisicaoAgendamento getRequisicao() {
        return requisicao;
    }

    public void setRequisicao(RequisicaoAgendamento requisicao) {
        this.requisicao = requisicao;
    }
}
