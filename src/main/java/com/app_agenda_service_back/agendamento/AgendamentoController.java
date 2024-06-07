package com.app_agenda_service_back.agendamento;

import com.app_agenda_service_back.servico.ServicoService;
import com.app_agenda_service_back.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ServicoService servicoService;

    @Autowired
    private AgendamentoMapper agendamentoMapper;

    @GetMapping
    public ResponseEntity<List<AgendamentoDTO>> getAllAgendamentos() {
        List<AgendamentoDTO> AgendamentosDTO = agendamentoService.getAllAgendamentos();
        return ResponseEntity.ok(AgendamentosDTO);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoDTO> getAgendamentoById(@PathVariable Long id) {
        AgendamentoDTO agendamentoDTO = agendamentoService.getAgendamentoById(id);
        return ResponseEntity.ok(agendamentoDTO);
    }
    @PostMapping
    public ResponseEntity<AgendamentoDTO> createAgendamento(@RequestBody AgendamentoEntity agendamento) {
        AgendamentoDTO createAgendamentoDTO = agendamentoService.createAgendamento(agendamento);
        return
                ResponseEntity.status(HttpStatus.CREATED).body(createAgendamentoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendamentoDTO> updateAgendamento(@PathVariable Long id,  @RequestBody AgendamentoDTO agendamentoDTO) {
        AgendamentoDTO updateAgendamentoDTO = agendamentoService.updateAgendamento(id,agendamentoDTO);
        return ResponseEntity.ok(updateAgendamentoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgendamento(@PathVariable Long id) {
        agendamentoService.deleteAgendamentoById(id);
        return ResponseEntity.noContent().build();
    }

}
