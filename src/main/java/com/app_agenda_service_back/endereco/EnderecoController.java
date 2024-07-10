package com.app_agenda_service_back.endereco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> getAllEnderecos(){
        List<EnderecoDTO> enderecosDTO = enderecoService.findAll();
        return ResponseEntity.ok(enderecosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> getEnderecoById(@PathVariable Long id){
        EnderecoDTO enderecoDTO = enderecoService.findById(id);
        return ResponseEntity.ok(enderecoDTO);
    }

    @PostMapping
    public ResponseEntity<EnderecoDTO> createEndereco(@RequestBody EnderecoDTO enderecoDTO){
        EnderecoDTO createEnderecoDTO = enderecoService.create(enderecoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createEnderecoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDTO> updateEndereco(@PathVariable Long id, @RequestBody EnderecoDTO enderecoDTO){
        EnderecoDTO updateEnderecoDTO = enderecoService.update(id,enderecoDTO);
        return ResponseEntity.ok(updateEnderecoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEndereco(@PathVariable Long id){
        enderecoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
