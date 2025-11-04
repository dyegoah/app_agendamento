package br.com.higino.app_agendamento.model;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
public class Agendamento {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String cliente;
  private LocalDateTime dataHora;

  @ManyToOne @JoinColumn(name = "servico_id")
  private Servico servico;

  @ManyToOne @JoinColumn(name = "loja_id")
  private Loja loja;

  private String status; // PENDENTE, PAGO, CANCELADO
  private BigDecimal valor;

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getCliente() { return cliente; }
  public void setCliente(String cliente) { this.cliente = cliente; }
  public LocalDateTime getDataHora() { return dataHora; }
  public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
  public Servico getServiço() { return servico; }
  public void setServiço(Servico servico) { this.servico = servico; }
  public Loja getLoja() { return loja; }
  public void setLoja(Loja loja) { this.loja = loja; }
  public String getStatus() { return status; }
  public void setStatus(String status) { this.status = status; }
  public BigDecimal getValor() { return valor; }
  public void setValor(BigDecimal valor) { this.valor = valor; }
}
