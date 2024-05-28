package com.app_agenda_service_back.prestador;

import com.app_agenda_service_back.endereco.EnderecoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrestadorService {

    @Autowired
    private PrestadorRepository prestadorRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private PrestadorMapper prestadorMapper;

    public List<PrestadorDTO> findAll(){
        List<PrestadorEntity> prestadores = prestadorRepository.findAll();
        return prestadorMapper.toDTOList(prestadores);
        //return prestadores.stream().map(prestadorMapper::toDTO).collect(Collectors.toList());
    }

    public PrestadorDTO findById(Long id){
        PrestadorEntity prestador = prestadorRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Prestador não encontrado"));
        return prestadorMapper.toDTO(prestador);
    }

    // o create de usuario está diferente, verificar com Filipe

    @Transactional
    public PrestadorDTO create(PrestadorEntity prestador){
        prestadorRepository.save(prestador);
        return prestadorMapper.toDTO(prestador);
    }

    //tem que fzer os get e set?
    @Transactional
    public PrestadorDTO update(Long id,PrestadorDTO prestadorDTO){
        PrestadorEntity prestador = prestadorRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Prestador não encontrado"));
        prestadorDTO.setPrestadorId(id);
        prestador = prestadorMapper.updateEntity(prestadorDTO, prestador);
        prestador = prestadorRepository.save(prestador);
        return prestadorMapper.toDTO(prestador);
    }

    public void deleteById(Long id){
        prestadorRepository.deleteById(id);
    }
}
