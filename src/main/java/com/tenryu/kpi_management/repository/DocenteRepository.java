package com.tenryu.kpi_management.repository;

import com.tenryu.kpi_management.model.Docente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Long> {
    @Query("SELECT d FROM Docente d WHERE LOWER(d.nombre) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(d.apellido) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<Docente> searchByNameOrSurname(@Param("searchTerm") String searchTerm, Pageable pageable);
}
