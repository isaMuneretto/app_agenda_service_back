package com.app_agenda_service_back.categoria;

import com.app_agenda_service_back.servico.ServicoDTO;
import com.app_agenda_service_back.servico.ServicoEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long categoriaId;

    @NotNull(message = "O campo NOME é requerido")
    private String categoriaNome;

    @NotNull(message = "O campo DESCRICAO é requerido")
    private String categoriaDescricao;

    private List<ServicoDTO> servicos;
}
