package br.com.higino.app_agendamento.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Representa um serviço oferecido por uma loja.
 * Exemplo: Maquiagem básica, Maquiagem de festa, etc.
 */
@Entity
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "loja_id")
    private Loja loja;

    // 🔹 Construtor padrão exigido pelo JPA
    public Servico() {
    }

    // 🔹 Construtor usado para criar serviços automaticamente no AdminController
    public Servico(String nome, Double valor, Loja loja) {
        this.nome = nome;
        this.valor = valor;
        this.loja = loja;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }
}
