package com.app_agenda_service_back.agendamento;

public enum AgendamentoStatus {
    PENDENTE("Pendente"), CONCLUIDO("Concluído"),
    CANCELADO("Cancelado");
    private final String descricao;
    AgendamentoStatus(String descricao){ this.descricao = descricao; }
    @Override
    public String toString(){ return descricao; }
}
