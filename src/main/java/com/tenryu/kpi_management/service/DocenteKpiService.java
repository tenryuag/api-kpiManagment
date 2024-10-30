package com.tenryu.kpi_management.service;

import com.tenryu.kpi_management.exceptions.ResourceNotFoundException;
import com.tenryu.kpi_management.model.DocenteKpi;
import com.tenryu.kpi_management.repository.DocenteKpiRepository;
import com.tenryu.kpi_management.response.DocenteKpiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocenteKpiService {
    @Autowired
    private DocenteKpiRepository docenteKpiRepository;

    // Obtener los KPI's de un docente
    public List<DocenteKpi> getKpisByDocente(Long docenteId) {
        return docenteKpiRepository.findByDocenteId(docenteId);
    }

    // Crear una nueva relación docente-KPI
    public DocenteKpi createDocenteKpi(DocenteKpi docenteKpi) {
        return docenteKpiRepository.save(docenteKpi);
    }

    // Eliminar una relación docente-KPI
    public void deleteDocenteKpi(Long id) {
        docenteKpiRepository.deleteByKpiId(id);
    }

    public DocenteKpi updateKpiForDocente(Long docenteKpiId, DocenteKpi updatedDocenteKpi) {
        DocenteKpi docenteKpi = docenteKpiRepository.findById(docenteKpiId).orElseThrow(() -> new ResourceNotFoundException("DocenteKpi not found"));

        docenteKpi.setValor(updatedDocenteKpi.getValor());
        docenteKpi.setObjetivo(updatedDocenteKpi.getObjetivo());
        docenteKpi.setPorcentajeActual(updatedDocenteKpi.getPorcentajeActual());
        docenteKpi.setTotalSemanal(updatedDocenteKpi.getTotalSemanal());
        docenteKpi.setTotalMensual(updatedDocenteKpi.getTotalMensual());
        docenteKpi.setTotalTrimestral(updatedDocenteKpi.getTotalTrimestral());
        docenteKpi.setTotalAnual(updatedDocenteKpi.getTotalAnual());
        docenteKpi.setCumplimientoDiario(updatedDocenteKpi.getCumplimientoDiario());

        return docenteKpiRepository.save(docenteKpi);
    }

    public DocenteKpi getKpiForDocente(Long docenteKpiId) {
        return docenteKpiRepository.findById(docenteKpiId).orElseThrow(() -> new ResourceNotFoundException("DocenteKpi not found"));
    }

    public List<DocenteKpiResponse> findKpiNamesAndDocenteNameByDocenteId(Long docenteId) {
        return docenteKpiRepository.findKpiNamesAndDocenteNameByDocenteId(docenteId);
    }
}
