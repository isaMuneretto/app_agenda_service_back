package com.app_agenda_service_back.usuario;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private static final String SECRET_KEY = "yourSecretKey";
    private static final long EXPIRATION_TIME = 3600000;

    public String authenticate(String usuarioEmail, String usuarioSenha) {
        UsuarioEntity usuario = usuarioRepository.findByUsuarioEmail(usuarioEmail);
        System.out.println("usuário:" + usuario);
        if(usuario != null && usuario.getUsuarioSenha().equals(usuarioSenha)) {
            return Jwts.builder()
                    .setSubject(usuario.getUsuarioEmail())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(SECRET_KEY.getBytes()))
                    .compact();
        }
        return null;
    }
}
