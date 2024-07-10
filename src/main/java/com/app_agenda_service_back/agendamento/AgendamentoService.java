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

    public List<AgendamentoDTO> findAll() {
        List<AgendamentoEntity> agendamentos = agendamentoRepository.findAll();
        System.out.println(agendamentos);
        return agendamentoMapper.toDTOList(agendamentos);
    }

    public AgendamentoDTO findById(Long id) {
        AgendamentoEntity agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento not found with id: " + id));
        return agendamentoMapper.toDTO(agendamento);
    }

    public AgendamentoDTO create(AgendamentoDTO agendamentoDTO) {
        AgendamentoEntity agendamento = agendamentoMapper.toEntity(agendamentoDTO);
        agendamento.setAgendamentoStatus(AgendamentoStatus.PENDENTE);
        agendamento = agendamentoRepository.save(agendamento);
        return agendamentoMapper.toDTO(agendamento);
    }

    @Transactional
    public AgendamentoDTO update(Long id, AgendamentoDTO agendamentoDTO) {
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

    public void deleteById(Long id) {
        agendamentoRepository.deleteById(id);
    }
}
