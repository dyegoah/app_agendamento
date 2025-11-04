package br.com.higino.app_agendamento.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusLoja {
    EM_DIA,
    EM_ATRASO;

    @JsonCreator
    public static StatusLoja fromValue(String value) {
        return value == null ? null : StatusLoja.valueOf(value.toUpperCase());
    }

    @JsonValue
    public String toValue() {
        return this.name();
    }
}
