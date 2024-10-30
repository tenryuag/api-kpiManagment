package com.tenryu.kpi_management.repository;

import com.tenryu.kpi_management.model.DocenteKpi;
import com.tenryu.kpi_management.response.DocenteKpiResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DocenteKpiRepository extends JpaRepository<DocenteKpi, Long> {

    List<DocenteKpi> findByDocenteId(Long docenteId);

    // Méto do para eliminar todas las relaciones DocenteKpi asociadas con un KPI
    @Modifying
    @Query("DELETE FROM DocenteKpi dk WHERE dk.kpi.id = :kpiId")
    void deleteByKpiId(@Param("kpiId") Long kpiId);

    @Query("SELECT d.nombre AS docenteNombre, d.apellido AS docenteApellido, k.nombre AS kpiNombre, k.id AS kpiId, dk.id AS docenteKpiId, dk.valor AS valor " +
            "FROM DocenteKpi dk " +
            "JOIN dk.docente d " +
            "JOIN dk.kpi k " +
            "WHERE d.id = :docenteId")
    List<DocenteKpiResponse> findKpiNamesAndDocenteNameByDocenteId(@Param("docenteId") Long docenteId);
}
