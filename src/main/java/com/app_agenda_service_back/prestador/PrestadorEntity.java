package com.app_agenda_service_back.prestador;

import com.app_agenda_service_back.endereco.EnderecoEntity;
import com.app_agenda_service_back.servico.ServicoEntity;
import com.app_agenda_service_back.telefone.TelefoneEntity;
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
@Table(name = "prestador")
public class PrestadorEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prestadorId;

    private String prestadorNome;

    private String prestadorCnpj;

    private String prestadorEmail;

    private String prestadorRazaoSocial;

    private String prestadorSenha;

    private String prestadorCpf;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "prestador_enderecoId")
    private EnderecoEntity endereco;

    @OneToMany(fetch = FetchType.EAGER)
    private List<ServicoEntity> servicos;

    @OneToMany( fetch = FetchType.EAGER)
    private List<TelefoneEntity> telefones;
}
