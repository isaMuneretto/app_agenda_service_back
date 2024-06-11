package com.app_agenda_service_back.telefone;

import com.app_agenda_service_back.prestador.PrestadorEntity;
import com.app_agenda_service_back.usuario.UsuarioEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "telefone")
public class TelefoneEntity implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long telefoneId;

    @Column(nullable = false,length = 45)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "(XX) XXXX-XXXX")
    private String telefoneNumero;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "telefone_usuarioId")
    private UsuarioEntity usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "telefone_prestadorId")
    private PrestadorEntity prestador;

}
