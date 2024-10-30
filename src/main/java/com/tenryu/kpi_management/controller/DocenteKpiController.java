package com.tenryu.kpi_management.controller;

import com.tenryu.kpi_management.model.DocenteKpi;
import com.tenryu.kpi_management.response.DocenteKpiResponse;
import com.tenryu.kpi_management.service.DocenteKpiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/docente-kpis")
@CrossOrigin(origins = "http://localhost:3000")
public class DocenteKpiController {

    @Autowired
    private DocenteKpiService docenteKpiService;

    // Obtener los KPI's de un docente por su ID
    @GetMapping("/{docenteId}")
    public List<DocenteKpi> getKPIsByDocente(@PathVariable Long docenteId) {
        return docenteKpiService.getKpisByDocente(docenteId);
    }

    // Crear una nueva relación docente-KPI
    @PostMapping
    public DocenteKpi createDocenteKPI(@RequestBody DocenteKpi docenteKPI) {
        return docenteKpiService.createDocenteKpi(docenteKPI);
    }

    // Eliminar una relación docente-KPI
    @DeleteMapping("/{id}")
    public void deleteDocenteKPI(@PathVariable Long id) {
        docenteKpiService.deleteDocenteKpi(id);
    }

    @PutMapping("/{docenteKpiId}")
    public ResponseEntity<DocenteKpi> updateDocenteKpi(
            @PathVariable Long docenteKpiId,
            @RequestBody DocenteKpi updatedDocenteKpi
    ) {
        DocenteKpi updatedKpi = docenteKpiService.updateKpiForDocente(docenteKpiId, updatedDocenteKpi);
        return new ResponseEntity<>(updatedKpi, HttpStatus.OK);
    }

    @GetMapping("/nombres/{docenteId}")
    public List<DocenteKpiResponse> getKpiNamesAndDocenteName(@PathVariable Long docenteId) {
        return docenteKpiService.findKpiNamesAndDocenteNameByDocenteId(docenteId);
    }

}
