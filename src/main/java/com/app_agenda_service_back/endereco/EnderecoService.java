package com.app_agenda_service_back.endereco;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoMapper enderecoMapper;

    public List<EnderecoDTO> findAll(){
        List<EnderecoEntity> enderecos = enderecoRepository.findAll();
        return enderecos.stream().map(enderecoMapper::toDTO).collect(Collectors.toList());
    }

    //buscar um endereço pelo id
    public EnderecoDTO findById(Long id){
        EnderecoEntity endereco = enderecoRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Endereço não encontrado"));
        return enderecoMapper.toDTO(endereco);
    }

    //cria um endereço
    @Transactional
    public EnderecoDTO create(EnderecoDTO enderecoDTO){
        EnderecoEntity endereco = enderecoMapper.toEntity(enderecoDTO);
        endereco = enderecoRepository.save(endereco);

        return enderecoMapper.toDTO(endereco);
    }

    //atualiza um endereco
    @Transactional
    public EnderecoDTO update(Long id,EnderecoDTO enderecoDTO){
        EnderecoEntity endereco = enderecoRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Endereço não encontrado"));
        enderecoDTO.setEnderecoId(endereco.getEnderecoId()); //setar o id para nao criar um novo
        endereco = enderecoMapper.updateEntity(enderecoDTO,endereco);
        endereco = enderecoRepository.save(endereco);
        return enderecoMapper.toDTO(endereco);
    }

    //deletar endereço
    public void deleteById(Long id) {
        enderecoRepository.deleteById(id);
    }
}


