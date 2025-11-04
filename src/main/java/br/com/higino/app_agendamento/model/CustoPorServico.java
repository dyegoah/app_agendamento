package br.com.higino.app_agendamento.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "custo_por_servico")
public class CustoPorServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String servico;
    private Double valorVenda;
    private Double valorCusto;
    private Double lucro;

    private LocalDate dataCalculo;

    @Column(columnDefinition = "TEXT")
    private String itensJson; // JSON dos insumos usados

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loja_id")
    private Loja loja;

    public CustoPorServico() {}

    public CustoPorServico(String servico, Double valorVenda, Double valorCusto, Double lucro, String itensJson, Loja loja) {
        this.servico = servico;
        this.valorVenda = valorVenda;
        this.valorCusto = valorCusto;
        this.lucro = lucro;
        this.itensJson = itensJson;
        this.loja = loja;
        this.dataCalculo = LocalDate.now();
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getServico() { return servico; }
    public void setServico(String servico) { this.servico = servico; }

    public Double getValorVenda() { return valorVenda; }
    public void setValorVenda(Double valorVenda) { this.valorVenda = valorVenda; }

    public Double getValorCusto() { return valorCusto; }
    public void setValorCusto(Double valorCusto) { this.valorCusto = valorCusto; }

    public Double getLucro() { return lucro; }
    public void setLucro(Double lucro) { this.lucro = lucro; }

    public String getItensJson() { return itensJson; }
    public void setItensJson(String itensJson) { this.itensJson = itensJson; }

    public LocalDate getDataCalculo() { return dataCalculo; }
    public void setDataCalculo(LocalDate dataCalculo) { this.dataCalculo = dataCalculo; }

    public Loja getLoja() { return loja; }
    public void setLoja(Loja loja) { this.loja = loja; }
}
