package com.app_agenda_service_back.servico;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServicoMapper {

    @Mapping(source = "servicoId", target = "servicoId")
    ServicoDTO toDTO(ServicoEntity servico);

    @Mapping(source = "servicoDTO.servicoId", target = "servicoId")
    ServicoEntity toEntity(ServicoDTO servicoDTO);

    List<ServicoDTO> toDTOList(List<ServicoEntity> servicos);

    @Mappings({
            @Mapping(source = "servicoDTO.servicoId", target = "servicoId"),
            @Mapping(source = "servicoDTO.servicoNome", target = "servicoNome"),
            @Mapping(source = "servicoDTO.servicoPreco", target = "servicoPreco"),
            @Mapping(source = "servicoDTO.servicoDescricao", target = "servicoDescricao"),
            @Mapping(source = "servicoDTO.servicoInformacoesExtras", target = "servicoInformacoesExtras"),
            @Mapping(source = "servicoDTO.servicoClassificacao", target = "servicoClassificacao"),
            @Mapping(source = "servicoDTO.categoria", target = "categoria"),
            @Mapping(source = "servicoDTO.prestador", target = "prestador")
    })
    ServicoEntity updateEntity(ServicoDTO servicoDTO, ServicoEntity servico);
}
