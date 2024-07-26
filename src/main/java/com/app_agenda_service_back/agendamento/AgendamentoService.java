package com.app_agenda_service_back.agendamento;

import com.app_agenda_service_back.servico.ServicoEntity;
import com.app_agenda_service_back.usuario.UsuarioEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    AgendamentoMapper agendamentoMapper;

    public List<AgendamentoDTO> findAll() {
        List<AgendamentoEntity> agendamentos = agendamentoRepository.findAll();
        return agendamentos.stream().map(agendamentoMapper::toDTO).collect(Collectors.toList());
        //return agendamentoMapper.toDTOList(agendamentos);
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
        AgendamentoEntity agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Agendamento não encontrado"));

        agendamentoDTO.setAgendamentoId(id);
        agendamento = agendamentoMapper.updateEntity(agendamentoDTO, agendamento);
        agendamento = agendamentoRepository.save(agendamento);
        return agendamentoMapper.toDTO(agendamento);
    }

    public void deleteById(Long id) {
        agendamentoRepository.deleteById(id);
    }
}
