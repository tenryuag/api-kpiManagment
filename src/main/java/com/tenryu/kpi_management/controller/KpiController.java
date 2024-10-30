package com.tenryu.kpi_management.controller;

import com.tenryu.kpi_management.model.Docente;
import com.tenryu.kpi_management.model.DocenteKpi;
import com.tenryu.kpi_management.model.Kpi;
import com.tenryu.kpi_management.service.DocenteKpiService;
import com.tenryu.kpi_management.service.DocenteService;
import com.tenryu.kpi_management.service.KpiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kpis")
@CrossOrigin(origins = "http://localhost:3000")
public class KpiController {

    @Autowired
    private KpiService kpiService;

    @Autowired
    private DocenteService docenteService;

    @Autowired
    private DocenteKpiService docenteKpiService;

    @GetMapping
    public List<Kpi> getAllKpis() {
        return kpiService.getAllKpis();
    }

    @PostMapping
    public Kpi createKpi(@RequestBody Kpi kpi) {
        Kpi newKPI = kpiService.createKpi(kpi);

        List<Docente> docentes = docenteService.getAllDocentes(Pageable.unpaged()).getContent();

        for (Docente docente : docentes) {
            DocenteKpi docenteKpi = new DocenteKpi();
            docenteKpi.setDocente(docente);
            docenteKpi.setKpi(newKPI);
            docenteKpi.setValor(0);  // Inicializa el valor a 0 o cualquier otro valor por defecto
            docenteKpiService.createDocenteKpi(docenteKpi);
        }
        return kpiService.createKpi(kpi);
    }

    @DeleteMapping("/{kpiId}")
    public ResponseEntity<String> deleteKpi(@PathVariable Long kpiId) {
        // Verificar si el KPI existe
        Kpi kpi = kpiService.getKpiById(kpiId);
        if (kpi == null) {
            return new ResponseEntity<>("KPI no encontrado", HttpStatus.NOT_FOUND);
        }

        // Eliminar todas las relaciones DocenteKpi asociadas con el KPI
        docenteKpiService.deleteDocenteKpi(kpiId);

        // Eliminar el KPI de la base de datos
        kpiService.deleteKpi(kpiId);

        return new ResponseEntity<>("KPI eliminado exitosamente", HttpStatus.OK);
    }

}
