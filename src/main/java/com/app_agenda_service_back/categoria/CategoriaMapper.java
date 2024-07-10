package com.app_agenda_service_back.categoria;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    @Mapping(source = "categoriaId", target = "categoriaId")
    CategoriaDTO toDTO(CategoriaEntity categoria);

    @Mapping(source = "categoriaDTO.categoriaId", target = "categoriaId")
    CategoriaEntity toEntity(CategoriaDTO categoriaDTO);

    List<CategoriaDTO> toDTO(List<CategoriaEntity> categorias);

    @Mappings({
            @Mapping(source = "categoriaDTO.categoriaId", target = "categoriaId"),
            @Mapping(source = "categoriaDTO.categoriaNome", target = "categoriaNome"),
            @Mapping(source = "categoriaDTO.categoriaDescricao", target = "categoriaDescricao"),
            @Mapping(source = "categoriaDTO.servicos", target = "servicos"),
    })
    CategoriaEntity updateEntity(CategoriaDTO categoriaDTO, CategoriaEntity categoria);

}
