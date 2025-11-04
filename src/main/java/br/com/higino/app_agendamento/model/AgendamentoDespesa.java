package br.com.higino.app_agendamento.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "agendamento_despesa")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoDespesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoDespesa;      // mensal, anual, profissional, servico, etc.
    private String item;
    private String descricao;
    private Integer quantidade;
    private Double valorUnitario;
    private Double valorTotal;
    private String vencimento;

    // 🔹 Novos campos específicos para tabela "Serviços e Valores por Agendamento"
    private String servico;
    private Double valor;

    // 🔹 Identificação da tabela HTML (para saber de onde veio o dado)
    private String nomeTabela;

    // 🔹 Integração com loja já existente
    @ManyToOne
    @JoinColumn(name = "loja_id")
    private Loja loja;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoDespesa() {
		return tipoDespesa;
	}

	public void setTipoDespesa(String tipoDespesa) {
		this.tipoDespesa = tipoDespesa;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getVencimento() {
		return vencimento;
	}

	public void setVencimento(String vencimento) {
		this.vencimento = vencimento;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getNomeTabela() {
		return nomeTabela;
	}

	public void setNomeTabela(String nomeTabela) {
		this.nomeTabela = nomeTabela;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}
    
    
}
