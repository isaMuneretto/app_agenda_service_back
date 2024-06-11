package com.app_agenda_service_back.telefone;

import com.app_agenda_service_back.servico.ServicoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/telefone")
public class TelefoneController {

    @Autowired
    private TelefoneService telefoneService;

    @GetMapping
    public ResponseEntity<List<TelefoneDTO>> getAllTelefones() {
        List<TelefoneDTO> telefones = telefoneService.findAll();
        return new ResponseEntity<>(telefones, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TelefoneDTO> getTelefoneById(@PathVariable Long id){
        TelefoneDTO telefone = telefoneService.findById(id);
        return new ResponseEntity<>(telefone, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TelefoneDTO> createTelefone(@RequestBody TelefoneDTO telefoneDTO) {
        TelefoneDTO createTelefoneDTO = telefoneService.create(telefoneDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createTelefoneDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TelefoneDTO> updateTelefone(@PathVariable Long id, @RequestBody TelefoneDTO telefoneDTO) {
        System.out.println("telefoneDTO "+ telefoneDTO);
        TelefoneDTO updatedTelefoneDTO = telefoneService.update(id, telefoneDTO);

        return ResponseEntity.ok(updatedTelefoneDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTelefone(@PathVariable Long id) {
        telefoneService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
