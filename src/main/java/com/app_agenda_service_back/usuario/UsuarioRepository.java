package com.app_agenda_service_back.usuario;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<UsuarioEntity,Long> {
    UsuarioEntity findByUsuarioEmail(String usuarioEmail);
}
