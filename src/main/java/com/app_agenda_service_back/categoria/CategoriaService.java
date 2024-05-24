package com.app_agenda_service_back.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaMapper categoriaMapper;

    public List<CategoriaDTO> findAll(){
        List<CategoriaEntity> categorias = categoriaRepository.findAll();
        return categorias.stream().map(categoriaMapper::toDTO).collect(Collectors.toList());
    }

    public CategoriaDTO findById(Long id){
        CategoriaEntity categoria = categoriaRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Categoria não encontrada"));
        return categoriaMapper.toDTO(categoria);
    }

    public CategoriaDTO create(CategoriaDTO categoriaDTO){
        CategoriaEntity categoria = categoriaMapper.toEntity(categoriaDTO);
        categoria = categoriaRepository.save(categoria);

        return categoriaMapper.toDTO(categoria);
    }

    public CategoriaDTO update(Long id, CategoriaDTO categoriaDTO){
        CategoriaEntity categoria = categoriaRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Categoria não encontrada"));
        categoriaDTO.setCategoriaId(id);
        categoria = categoriaMapper.updateEntity(categoriaDTO,categoria);
        categoria = categoriaRepository.save(categoria);
        return categoriaMapper.toDTO(categoria);
    }

    public void deleteById(Long id) {
        categoriaRepository.deleteById(id);
    }
}
