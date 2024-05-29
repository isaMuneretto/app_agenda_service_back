package com.app_agenda_service_back.prestador;

import com.app_agenda_service_back.endereco.EnderecoEntity;
import com.app_agenda_service_back.servico.ServicoEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrestadorDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long prestadorId;

    @NotNull(message = "O campo NOME é requerido")
    private String prestadorNome;

    @CNPJ
    private String prestadorCnpj;

    @NotNull(message = "O campo E-MAIL é requerido")
    private String prestadorEmail;

    private String prestadorRazaoSocial;

    @NotNull(message = "O campo SENHA é requerido")
    private String prestadorSenha;

    @CPF
    @NotNull(message = "O campo CPF é requerido")
    private String prestadorCpf;

    private EnderecoEntity endereco;

    private List<ServicoEntity> servicos = new ArrayList<>();
}
