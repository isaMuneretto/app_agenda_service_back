package com.app_agenda_service_back.endereco;

import com.app_agenda_service_back.usuario.UsuarioEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long enderecoId;

    @NotNull(message = "O campo RUA é requerido")
    private String enderecoRua;

    @NotNull(message = "O campo CEP é requerido")
    private String enderecoCep;

    @NotNull(message = "O campo NUMERO é requerido")
    private Integer enderecoNumero;

    private String enderecoComplemento;

    @NotNull(message = "O campo CIDADE é requerido")
    private String enderecoCidade;

    @NotNull(message = "O campo ESTADO é requerido")
    private String enderecoEstado;

    @NotNull(message = "O campo BAIRRO é requerido")
    private String enderecoBairro;

    @JsonIgnore
    private List<UsuarioEntity> usuarios = new ArrayList<>();

}
