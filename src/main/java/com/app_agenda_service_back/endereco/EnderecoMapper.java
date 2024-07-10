package com.app_agenda_service_back.endereco;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    @Mapping(source = "enderecoId", target = "enderecoId")
    EnderecoDTO toDTO(EnderecoEntity endereco);

    @Mapping(source = "enderecoDTO.enderecoId", target = "enderecoId")
    EnderecoEntity toEntity(EnderecoDTO enderecoDTO);

    List<EnderecoDTO> toDTO(List<EnderecoEntity> enderecos);

    @Mappings({
            @Mapping(source = "enderecoDTO.enderecoId", target = "enderecoId"),
            @Mapping(source = "enderecoDTO.enderecoRua", target = "enderecoRua"),
            @Mapping(source = "enderecoDTO.enderecoCep", target = "enderecoCep"),
            @Mapping(source = "enderecoDTO.enderecoCidade", target = "enderecoCidade"),
            @Mapping(source = "enderecoDTO.enderecoBairro", target = "enderecoBairro"),
            @Mapping(source = "enderecoDTO.enderecoEstado", target = "enderecoEstado"),
            @Mapping(source = "enderecoDTO.enderecoNumero", target = "enderecoNumero"),
            @Mapping(source = "enderecoDTO.enderecoComplemento", target = "enderecoComplemento"),
            @Mapping(source = "enderecoDTO.usuarios", target = "usuarios"),
            @Mapping(source = "enderecoDTO.prestadores", target = "prestadores")
    })
    EnderecoEntity updateEntity(EnderecoDTO enderecoDTO, EnderecoEntity endereco);
}
