package com.tenryu.kpi_management.service;

import com.tenryu.kpi_management.model.Kpi;
import com.tenryu.kpi_management.repository.KpiRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KpiService {

    @Autowired
    private KpiRepository kpiRepository;

    public List<Kpi> getAllKpis() {
        return kpiRepository.findAll();
    }

    public Kpi getKpiById(Long id) {
        return kpiRepository.findById(id).orElse(null);
    }

    public Kpi createKpi(Kpi kpi) {
        return kpiRepository.save(kpi);
    }

    public void deleteKpi(Long id) {
        kpiRepository.deleteById(id);
    }

    public Kpi updateKpi(Kpi kpi) {
        return kpiRepository.save(kpi);
    }
}
