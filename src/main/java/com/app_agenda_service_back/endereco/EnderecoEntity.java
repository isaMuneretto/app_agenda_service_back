package com.app_agenda_service_back.endereco;

import com.app_agenda_service_back.usuario.UsuarioEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "endereco")
public class EnderecoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enderecoId;

    private String enderecoRua;

    private String enderecoCep;

    private int enderecoNumero;

    private String enderecoComplemento;

    private String enderecoCidade;

    private String enderecoEstado;

    private String getEnderecoBairro;

    @OneToMany(mappedBy = "endereco", fetch = FetchType.EAGER)
    private List<UsuarioEntity> usuarios = new ArrayList<>();
}
