package com.app_agenda_service_back.servico;

import com.app_agenda_service_back.agendamento.AgendamentoEntity;
import com.app_agenda_service_back.categoria.CategoriaEntity;
import com.app_agenda_service_back.prestador.PrestadorEntity;
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
@Table(name = "servico")
public class ServicoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long servicoId;

    private String servicoNome;

    private Double servicoPreco;

    private String servicoDescricao;

    private String servicoInformacoesExtras;

    private String servicoClassificacao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "servico_categoriaId")
    private CategoriaEntity categoria;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "servico_prestadorId")
    private PrestadorEntity prestador;

    @OneToMany(fetch = FetchType.EAGER)
    private List<AgendamentoEntity> agendamentos;
}
