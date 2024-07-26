package com.app_agenda_service_back.prestador;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/prestador")
public class PrestadorController {

    @Autowired
    private PrestadorService prestadorService;

    @GetMapping
    public ResponseEntity<List<PrestadorDTO>> getAllPrestadores(){
        List<PrestadorDTO> prestadoresDTO = prestadorService.findAll();
        return ResponseEntity.ok(prestadoresDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestadorDTO> getPrestadorById(@PathVariable Long id){
        PrestadorDTO prestadorDTO = prestadorService.findById(id);
        return ResponseEntity.ok(prestadorDTO);
    }

    @PostMapping
    public ResponseEntity<PrestadorDTO> createPrestador(@Valid @RequestBody PrestadorDTO prestadorDTO){
        System.out.println("Dados do prestador:" + prestadorDTO);
        PrestadorDTO createPrestadorDTO = prestadorService.create(prestadorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createPrestadorDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrestadorDTO> updatePrestador(@PathVariable Long id, @Valid @RequestBody PrestadorDTO prestadorDTO){
        PrestadorDTO updatePrestadorDTO = prestadorService.update(id,prestadorDTO);
        return ResponseEntity.ok(updatePrestadorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrestador(@PathVariable Long id) {
        prestadorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<PrestadorDTO>> getPrestadoresByServicoNome(@RequestParam String servicoNome) {
        System.out.println("servicoNome:"+servicoNome);
        if (servicoNome == null || servicoNome.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }

        try {
            List<PrestadorDTO> prestadores = prestadorService.findByServicoNome(servicoNome);
            System.out.println("lista de prestadores:"+prestadores);
            return ResponseEntity.ok(prestadores);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }
}
