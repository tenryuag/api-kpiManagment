package com.tenryu.kpi_management.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Kpi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "la descripcion no puede estar vacía")
    private String descripcion;

    // Relación con DocenteKpi
    @OneToMany(mappedBy = "kpi", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "kpi-docenteKpi")
    private List<DocenteKpi> docenteKpis;


}
