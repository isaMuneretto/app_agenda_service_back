package com.app_agenda_service_back.agendamento;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AgendamentoMapper {

    @Mapping(source = "agendamentoId", target = "agendamentoId")
    AgendamentoDTO toDTO(AgendamentoEntity agendamento);

    @Mapping(source = "agendamentoDTO.agendamentoId", target = "agendamentoId")
    AgendamentoEntity toEntity(AgendamentoDTO agendamentoDTO);

    List<AgendamentoDTO> toDTOList(List<AgendamentoEntity> agendamentos);

    @Mappings({
            @Mapping(source = "agendamentoDTO.agendamentoId", target = "agendamentoId"),
            @Mapping(source = "agendamentoDTO.agendamentoData", target = "agendamentoData"),
            @Mapping(source = "agendamentoDTO.agendamentoHora", target = "agendamentoHora"),
            @Mapping(source = "agendamentoDTO.agendamentoObservacao", target = "agendamentoObservacao"),
            @Mapping(source = "agendamentoDTO.agendamentoStatus", target = "agendamentoStatus"),
            @Mapping(source = "agendamentoDTO.usuario", target = "usuario"),
            @Mapping(source = "agendamentoDTO.servico", target = "servico")
    })
    AgendamentoEntity updateEntity(AgendamentoDTO agendamentoDTO, AgendamentoEntity agendamento);
}
