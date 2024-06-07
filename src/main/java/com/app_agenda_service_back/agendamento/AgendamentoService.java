package com.app_agenda_service_back.agendamento;

import com.app_agenda_service_back.servico.ServicoEntity;
import com.app_agenda_service_back.servico.ServicoService;
import com.app_agenda_service_back.usuario.UsuarioEntity;
import com.app_agenda_service_back.usuario.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    AgendamentoMapper agendamentoMapper;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ServicoService servicoService;


    public List<AgendamentoDTO> getAllAgendamentos() {
        List<AgendamentoEntity> agendamentos = agendamentoRepository.findAll();
        System.out.println(agendamentos);
        return agendamentoMapper.toDTOList(agendamentos);
    }

    public AgendamentoDTO getAgendamentoById(Long id) {
        AgendamentoEntity agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento not found with id: " + id));
        return agendamentoMapper.toDTO(agendamento);
    }

    public AgendamentoDTO createAgendamento(AgendamentoEntity agendamento) {
        agendamento = agendamentoRepository.save(agendamento);
        return agendamentoMapper.toDTO(agendamento);
    }

    @Transactional
    public AgendamentoDTO updateAgendamento(Long id, AgendamentoDTO agendamentoDTO) {
        AgendamentoEntity agendamento = agendamentoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado"));

        //agendamentoDTO.setId(id);
        ServicoEntity servico = agendamento.getServico();
        agendamentoDTO.setServico(servico);
        UsuarioEntity usuario = agendamento.getUsuario();
        agendamentoDTO.setUsuario(usuario);
        System.out.println("dto " + agendamentoDTO);
        agendamento = agendamentoMapper.updateEntity(agendamentoDTO,agendamento);
        agendamento = agendamentoRepository.save(agendamento);
        agendamentoDTO = agendamentoMapper.toDTO(agendamento);

        return agendamentoDTO;
    }

    public void deleteAgendamentoById(Long id) {
        agendamentoRepository.deleteById(id);
    }
}
