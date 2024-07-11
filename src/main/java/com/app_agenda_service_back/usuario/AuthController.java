package com.app_agenda_service_back.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String usuarioEmail = credentials.get("usuarioEmail");
        String usuarioSenha = credentials.get("usuarioSenha");
        System.out.println(usuarioEmail);
        System.out.println(usuarioSenha);

        String token = authService.authenticate(usuarioEmail, usuarioSenha);
        if(token != null) {
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            System.out.println("deu tudo certo login");
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
