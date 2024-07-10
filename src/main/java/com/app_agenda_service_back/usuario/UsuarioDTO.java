package com.app_agenda_service_back.usuario;

import com.app_agenda_service_back.agendamento.AgendamentoDTO;
import com.app_agenda_service_back.endereco.EnderecoEntity;
import com.app_agenda_service_back.telefone.TelefoneDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long usuarioId;

    @NotBlank(message = "O campo NOME é requerido")
    private String usuarioNome;

    @CPF
    @NotBlank(message = "O campo CPF é requerido")
    private String usuarioCpf;

    @NotBlank(message = "O campo EMAIL é requerido")
    private String usuarioEmail;

    @NotBlank(message = "O campo SENHA é requerido")
    private String usuarioSenha;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "O campo DATA_NASCIMENTO é requerido")
    private LocalDate usuarioDataNascimento;

    private EnderecoEntity endereco;

    private List<TelefoneDTO> telefones;

    private List<AgendamentoDTO> agendamentos;
}
