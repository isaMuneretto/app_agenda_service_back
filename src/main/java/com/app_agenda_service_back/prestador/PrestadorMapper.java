package com.app_agenda_service_back.prestador;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PrestadorMapper {

    @Mapping(source = "prestadorId", target = "prestadorId")
    PrestadorDTO toDTO(PrestadorEntity prestador);

    @Mapping(source = "prestadorDTO.prestadorId", target = "prestadorId")
    PrestadorEntity toEntity(PrestadorDTO prestadorDTO);

    List<PrestadorDTO> toDTOList(List<PrestadorEntity> prestadores);

    @Mappings({
            @Mapping(source = "prestadorDTO.prestadorId", target = "prestadorId"),
            @Mapping(source = "prestadorDTO.prestadorNome", target = "prestadorNome"),
            @Mapping(source = "prestadorDTO.prestadorCnpj", target = "prestadorCnpj"),
            @Mapping(source = "prestadorDTO.prestadorEmail", target = "prestadorEmail"),
            @Mapping(source = "prestadorDTO.prestadorRazaoSocial", target = "prestadorRazaoSocial"),
            @Mapping(source = "prestadorDTO.prestadorSenha", target = "prestadorSenha"),
            @Mapping(source = "prestadorDTO.prestadorCpf", target = "prestadorCpf"),
            @Mapping(source = "prestadorDTO.endereco", target = "endereco"),
            @Mapping(source = "prestadorDTO.servicos", target = "servicos"),
            @Mapping(source = "prestadorDTO.telefones", target = "telefones"),
    })
    PrestadorEntity updateEntity(PrestadorDTO prestadorDTO, PrestadorEntity prestador);
}
