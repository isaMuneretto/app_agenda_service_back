package com.app_agenda_service_back.telefone;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TelefoneMapper {

    @Mappings({
            @Mapping(source = "telefoneId", target = "telefoneId"),
            @Mapping(source = "telefoneNumero", target = "telefoneNumero"),
            @Mapping(source = "usuario.usuarioId", target = "usuario.usuarioId"),
            @Mapping(source = "prestador.prestadorId", target = "prestador.prestadorId")
    })
    TelefoneDTO toDTO(TelefoneEntity telefone);

    @Mappings({
            @Mapping(source = "telefoneDTO.telefoneId", target = "telefoneId"),
            @Mapping(source = "telefoneDTO.telefoneNumero", target = "telefoneNumero"),
            @Mapping(source = "telefoneDTO.usuario.usuarioId", target = "usuario.usuarioId"),
            @Mapping(source = "telefoneDTO.prestador.prestadorId", target = "prestador.prestadorId")
    })
    TelefoneEntity toEntity(TelefoneDTO telefoneDTO);

    List<TelefoneDTO> toDTOList(List<TelefoneEntity> telefone);

    @Mappings({
            @Mapping(source = "telefoneDTO.telefoneId", target = "telefoneId"),
            @Mapping(source = "telefoneDTO.telefoneNumero", target = "telefoneNumero"),
            @Mapping(source = "telefoneDTO.usuario.usuarioId", target = "usuario.usuarioId"),
            @Mapping(source = "telefoneDTO.prestador.prestadorId", target = "prestador.prestadorId")
    })
    TelefoneEntity updateEntity(TelefoneDTO telefoneDTO, TelefoneEntity telefone);
}
