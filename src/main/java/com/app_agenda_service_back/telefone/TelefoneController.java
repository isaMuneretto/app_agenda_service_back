package com.app_agenda_service_back.telefone;

import com.app_agenda_service_back.servico.ServicoDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/telefone")
public class TelefoneController {

    @Autowired
    private TelefoneService telefoneService;

    @GetMapping
    public ResponseEntity<List<TelefoneDTO>> getAllTelefones() {
        List<TelefoneDTO> telefonesDTO = telefoneService.findAll();
        return new ResponseEntity<>(telefonesDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TelefoneDTO> getTelefoneById(@PathVariable Long id){
        TelefoneDTO telefoneDTO = telefoneService.findById(id);
        return new ResponseEntity<>(telefoneDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TelefoneDTO> createTelefone(@Valid @RequestBody TelefoneDTO telefoneDTO) {
        TelefoneDTO createTelefoneDTO = telefoneService.create(telefoneDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createTelefoneDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TelefoneDTO> updateTelefone(@PathVariable Long id, @Valid @RequestBody TelefoneDTO telefoneDTO) {
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
