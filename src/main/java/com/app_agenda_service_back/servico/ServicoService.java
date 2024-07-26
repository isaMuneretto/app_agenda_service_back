package com.app_agenda_service_back.servico;

import com.app_agenda_service_back.categoria.CategoriaService;
import com.app_agenda_service_back.prestador.PrestadorDTO;
import com.app_agenda_service_back.prestador.PrestadorEntity;
import com.app_agenda_service_back.prestador.PrestadorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private ServicoMapper servicoMapper;

    @Autowired
    private PrestadorService prestadorService;

    public List<ServicoDTO> findAll(){
        List<ServicoEntity> servicos = servicoRepository.findAll();
        return servicos.stream().map(servicoMapper::toDTO).collect(Collectors.toList());
    }

    public ServicoDTO findById(Long id){
        ServicoEntity servico = servicoRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Serviço não encontrado"));
        return servicoMapper.toDTO(servico);
    }

    @Transactional
    public ServicoDTO create(ServicoDTO servicoDTO){
        ServicoEntity servico = servicoMapper.toEntity(servicoDTO);
        servico = servicoRepository.save(servico);
        return servicoMapper.toDTO(servico);
    }

    @Transactional
    public ServicoDTO update(Long id,ServicoDTO servicoDTO){
        ServicoEntity servico = servicoRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Prestador não encontrado"));
        servicoDTO.setServicoId(id);
        servico = servicoMapper.updateEntity(servicoDTO, servico);
        servico = servicoRepository.save(servico);
        return servicoMapper.toDTO(servico);
    }

    public void deleteById(Long id){
        servicoRepository.deleteById(id);
    }
}
