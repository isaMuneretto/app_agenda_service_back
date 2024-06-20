package com.app_agenda_service_back.usuario;

import com.app_agenda_service_back.endereco.EnderecoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario")
@Entity
public class UsuarioEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioId;

    private String usuarioNome;

    private String usuarioCpf;

    private String usuarioEmail;

    private String usuarioSenha;

    private LocalDate usuarioDataNascimento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_enderecoId")
    private EnderecoEntity endereco;
}
