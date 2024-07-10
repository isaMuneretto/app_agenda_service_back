package com.app_agenda_service_back.usuario;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    @Mapping(source = "usuarioId", target = "usuarioId")
    UsuarioDTO toDTO(UsuarioEntity usuario);

    @Mapping(source = "usuarioDTO.usuarioId", target = "usuarioId")
    UsuarioEntity toEntity(UsuarioDTO usuarioDTO);

    List<UsuarioDTO> toDTO(List<UsuarioEntity> usuarios);


    @Mappings({
            @Mapping(source = "usuarioDTO.usuarioId", target = "usuarioId"),
            @Mapping(source = "usuarioDTO.usuarioCpf", target = "usuarioCpf"),
            @Mapping(source = "usuarioDTO.usuarioNome", target = "usuarioNome"),
            @Mapping(source = "usuarioDTO.usuarioEmail", target = "usuarioEmail"),
            @Mapping(source = "usuarioDTO.usuarioDataNascimento", target = "usuarioDataNascimento"),
            @Mapping(source = "usuarioDTO.usuarioSenha", target = "usuarioSenha"),
            @Mapping(source = "usuarioDTO.endereco", target = "endereco"),
            @Mapping(source = "usuarioDTO.telefones", target = "telefones"),
            @Mapping(source = "usuarioDTO.agendamentos", target = "agendamentos")
    })
    UsuarioEntity updateEntity(UsuarioDTO usuarioDTO, UsuarioEntity usuario);
}
