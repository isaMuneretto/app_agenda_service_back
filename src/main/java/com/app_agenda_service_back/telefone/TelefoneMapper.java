package com.app_agenda_service_back.telefone;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TelefoneMapper {
    @Mapping(source = "telefoneId", target = "telefoneId")
    TelefoneDTO toDTO(TelefoneEntity telefone);
    @Mapping(source = "telefoneDTO.telefoneId", target = "telefoneId")
    TelefoneEntity toEntity(TelefoneDTO telefoneDTO);

    //recebendo lista de telefone do banco em DTO
    List<TelefoneDTO> toDTO(List<TelefoneEntity> telefones);
    @Mappings({
            @Mapping(source = "telefoneDTO.telefoneId", target = "telefoneId"),
            @Mapping(source = "telefoneDTO.telefoneNumero", target = "telefoneNumero"),
            @Mapping(source = "telefoneDTO.usuario", target = "usuario"),
            @Mapping(source = "telefoneDTO.prestador", target = "prestador")
    })

    TelefoneEntity updateEntity(TelefoneDTO telefoneDTO, TelefoneEntity telefone);
}
