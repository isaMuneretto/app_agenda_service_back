package com.app_agenda_service_back.servico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServicoRepository extends JpaRepository<ServicoEntity,Long> {
    @Query("SELECT s FROM ServicoEntity s WHERE s.servicoNome = :servicoNome")
    List<ServicoEntity> findByServicoNome(@Param("servicoNome") String servicoNome);
}
