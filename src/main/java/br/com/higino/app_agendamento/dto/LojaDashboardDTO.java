package br.com.higino.app_agendamento.dto;

import java.math.BigDecimal;

public record LojaDashboardDTO(
  long agendamentos,
  String servicoMaisVendido,
  BigDecimal receitaMes,
  String proximoAgendamento // formato amigável
) {}
