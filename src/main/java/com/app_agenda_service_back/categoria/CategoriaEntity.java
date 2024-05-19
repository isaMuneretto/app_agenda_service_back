package com.app_agenda_service_back.categoria;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categoria")
public class CategoriaEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoriaId;

    private String categoriaNome;

    private String categoriaDescricao;

//    @OneToMany(mappedBy = "servico_id",fetch = FetchType.EAGER)
//    private List<ServicoEntity> servicos;

}
