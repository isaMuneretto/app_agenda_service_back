package com.app_agenda_service_back.agendamento;

import com.app_agenda_service_back.servico.ServicoEntity;
import com.app_agenda_service_back.usuario.UsuarioEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "agendamento")
public class AgendamentoEntity implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long agendamentoId;

    private LocalDate agendamentoData;

    @Temporal(TemporalType.TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm")
    private LocalTime agendamentoHora;

    private String agendamentoObservacao;

    @Enumerated(EnumType.STRING)
    private AgendamentoStatus agendamentoStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "agendamentoUsuarioId",nullable = false)
    private UsuarioEntity usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "agendamentoServicoId",nullable = false)
    private ServicoEntity servico;

}
