package com.app_agenda_service_back.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:5173"
@RestController
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @GetMapping
    public ResponseEntity<List<ServicoDTO>> getAllServicos() {
        List<ServicoDTO> servicos = servicoService.findAll();
        return new ResponseEntity<>(servicos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoDTO> getServicoById(@PathVariable Long id) {
        ServicoDTO servico = servicoService.findById(id);
        return new ResponseEntity<>(servico, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ServicoDTO> createServico(@RequestBody ServicoEntity servico) {
        ServicoDTO createServicoDTO = servicoService.create(servico);
        return ResponseEntity.status(HttpStatus.CREATED).body(createServicoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoDTO> updateServico(@PathVariable Long id, @RequestBody ServicoDTO servicoDTO) {
        System.out.println("servicoDTO "+ servicoDTO);
        ServicoDTO updatedServicoDTO = servicoService.update(id, servicoDTO);

        return ResponseEntity.ok(updatedServicoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServico(@PathVariable Long id) {
        servicoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
