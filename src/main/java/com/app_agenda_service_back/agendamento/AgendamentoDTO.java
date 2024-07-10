package com.app_agenda_service_back.agendamento;


import com.app_agenda_service_back.servico.ServicoEntity;
import com.app_agenda_service_back.usuario.UsuarioEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long agendamentoId; // Using Long instead of long for potential null values

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate agendamentoData;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime agendamentoHora;

    private String agendamentoObservacao;

    private AgendamentoStatus agendamentoStatus;

    private UsuarioEntity usuario;

    private ServicoEntity servico;
}
