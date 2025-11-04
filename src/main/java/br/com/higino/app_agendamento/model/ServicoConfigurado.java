package br.com.higino.app_agendamento.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "servicos_configurados")
public class ServicoConfigurado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String categoria;
    private String preco;        // armazenado como texto formatado (ex.: "25,90")
    private String descricao;

    @Column(length = 4000)
    private String imagemBase64; // imagem convertida em base64

    private String chavePix;
    private String whatsapp;
    private String valorEntrega; // valor individual opcional
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
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getPreco() {
		return preco;
	}
	public void setPreco(String preco) {
		this.preco = preco;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getImagemBase64() {
		return imagemBase64;
	}
	public void setImagemBase64(String imagemBase64) {
		this.imagemBase64 = imagemBase64;
	}
	public String getChavePix() {
		return chavePix;
	}
	public void setChavePix(String chavePix) {
		this.chavePix = chavePix;
	}
	public String getWhatsapp() {
		return whatsapp;
	}
	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}
	public String getValorEntrega() {
		return valorEntrega;
	}
	public void setValorEntrega(String valorEntrega) {
		this.valorEntrega = valorEntrega;
	}
}
