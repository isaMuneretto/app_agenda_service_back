package com.app_agenda_service_back.servico;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @GetMapping
    public ResponseEntity<List<ServicoDTO>> getAllServicos() {
        List<ServicoDTO> servicosDTO = servicoService.findAll();
        System.out.println("servicosDTO"+servicosDTO);
        return ResponseEntity.ok(servicosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoDTO> getServicoById(@PathVariable Long id) {
        ServicoDTO servicoDTO = servicoService.findById(id);
        return ResponseEntity.ok(servicoDTO);
    }

    @PostMapping
    public ResponseEntity<ServicoDTO> createServico(@Valid @RequestBody ServicoDTO servicoDTO) {
        ServicoDTO createServicoDTO = servicoService.create(servicoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createServicoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoDTO> updateServico(@PathVariable Long id, @Valid @RequestBody ServicoDTO servicoDTO) {
        System.out.println("servicoDTO "+ servicoDTO);
        ServicoDTO updatedServicoDTO = servicoService.update(id, servicoDTO);

        return ResponseEntity.ok(updatedServicoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServico(@PathVariable Long id) {
        servicoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
