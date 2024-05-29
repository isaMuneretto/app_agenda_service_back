package com.app_agenda_service_back.servico;

import com.app_agenda_service_back.categoria.CategoriaService;
import com.app_agenda_service_back.prestador.PrestadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private PrestadorService prestadorService;

    @Autowired
    private ServicoMapper servicoMapper;

    public List<ServicoDTO> findAll(){
        List<ServicoEntity> servicos = servicoRepository.findAll();
        return servicoMapper.toDTOList(servicos);
    }

    public ServicoDTO findById(Long id){
        ServicoEntity servico = servicoRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Serviço não encontrado");
        return servicoMapper.toDTO(servico);
    }


}
