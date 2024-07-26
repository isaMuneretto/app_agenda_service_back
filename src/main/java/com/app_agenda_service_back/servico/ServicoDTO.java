package com.app_agenda_service_back.servico;

import com.app_agenda_service_back.agendamento.AgendamentoEntity;
import com.app_agenda_service_back.categoria.CategoriaEntity;
import com.app_agenda_service_back.prestador.PrestadorEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long servicoId;

    @NotNull(message = "O campo NOME é requerido")
    private String servicoNome;

    @NotNull(message = "O campo PREÇO é requerido")
    private Double servicoPreco;

    @NotNull(message = "O campo DESCRICAO é requerido")
    private String servicoDescricao;

    private String servicoInformacoesExtras;

    @NotNull(message = "o campo CLASSIFICAÇÃO é requerido")
    private String servicoClassificacao;

    private CategoriaEntity categoria;

    private PrestadorEntity prestador;

    private List<AgendamentoEntity> agendamentos;

    public void setServicoId(Long id) {
    }
}
