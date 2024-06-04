package com.app_agenda_service_back.prestador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:5173"
@RestController
@RequestMapping("/prestadores")
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
    public ResponseEntity<PrestadorDTO> createPrestador(@RequestBody PrestadorEntity prestador){
        PrestadorDTO createPrestadorDTO = prestadorService.create(prestador);
        return ResponseEntity.status(HttpStatus.CREATED).body(createPrestadorDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrestadorDTO> updatePrestador(@PathVariable Long id, @RequestBody PrestadorDTO prestadorDTO){
        PrestadorDTO updatePrestadorDTO = prestadorService.update(id,prestadorDTO);
        return ResponseEntity.ok(updatePrestadorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrestador(@PathVariable Long id) {
        prestadorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
