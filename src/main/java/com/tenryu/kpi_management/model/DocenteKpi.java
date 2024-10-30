package com.tenryu.kpi_management.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class DocenteKpi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "docente_id", nullable = false)
    @JsonBackReference(value = "docente-kpi")
    private Docente docente;

    @ManyToOne
    @JoinColumn(name = "kpi_id", nullable = false)
    @JsonBackReference(value = "kpi-docenteKpi")
    private Kpi kpi;

    private int valor;

    private int objetivo;  // Objetivo del KPI

    private int porcentajeActual;  // Porcentaje alcanzado

    private int totalSemanal;

    private int totalMensual;

    private int totalTrimestral;

    private int totalAnual;

    // Campo para manejar el cumplimiento diario
    private boolean[] cumplimientoDiario = new boolean[5]; // Suponiendo que cada índice representa un día del año


}
