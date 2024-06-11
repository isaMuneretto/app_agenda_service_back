package com.app_agenda_service_back.telefone;

import com.app_agenda_service_back.prestador.PrestadorEntity;
import com.app_agenda_service_back.usuario.UsuarioEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelefoneDTO implements Serializable {

    public static final long serialVersionUID = 1L;

    private long telefoneId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "(XX) XXXX-XXXX")
    private String telefoneNumero;

    private UsuarioEntity usuario;
    private PrestadorEntity prestador;
}
