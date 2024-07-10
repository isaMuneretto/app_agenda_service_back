package com.app_agenda_service_back.prestador;

import com.app_agenda_service_back.endereco.EnderecoService;
import com.app_agenda_service_back.servico.ServicoEntity;
import com.app_agenda_service_back.servico.ServicoRepository;
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
    private ServicoRepository servicoRepository;

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

    @Transactional
    public PrestadorDTO create(PrestadorDTO prestadorDTO){
        PrestadorEntity prestador = prestadorMapper.toEntity(prestadorDTO);
        prestador = prestadorRepository.save(prestador);
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

    public List<PrestadorDTO> findByServicoNome(String servicoNome) {
        List<ServicoEntity> servicos = servicoRepository.findByServicoNome(servicoNome);
        List<PrestadorEntity> prestadores = servicos.stream()
                .map(ServicoEntity::getPrestador)
                .collect(Collectors.toList());
        return prestadores.stream().map(prestadorMapper::toDTO).collect(Collectors.toList());
    }
}
