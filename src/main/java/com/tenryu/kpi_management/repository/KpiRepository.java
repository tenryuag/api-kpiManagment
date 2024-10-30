package com.tenryu.kpi_management.repository;

import com.tenryu.kpi_management.model.Kpi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KpiRepository extends JpaRepository<Kpi, Long> {
}
