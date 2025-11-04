package br.com.higino.app_agendamento.dto;

import java.math.BigDecimal;

public record AdminStatsDTO(long totalLojas, long totalAgendamentos, BigDecimal receitaGeral) {}
