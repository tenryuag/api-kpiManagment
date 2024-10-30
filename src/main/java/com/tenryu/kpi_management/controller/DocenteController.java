package com.tenryu.kpi_management.controller;

import com.tenryu.kpi_management.model.Docente;
import com.tenryu.kpi_management.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/docentes")
public class DocenteController {

    @Autowired
    private DocenteService docenteService;

    @GetMapping
    public Page<Docente> getAllDocentes(@PageableDefault(size = 10, sort = "apellido", direction = Sort.Direction.ASC) Pageable pageable) {
        return docenteService.getAllDocentes(pageable);
    }

    @GetMapping("/{id}")
    public Docente getDocenteById(@PathVariable Long id) {
        return docenteService.getDocenteById(id);
    }

    @PostMapping
    public Docente createDocente(@RequestBody Docente docente) {
        return docenteService.createDocente(docente);
    }

    @DeleteMapping("/{id}")
    public void deleteDocente(@PathVariable Long id) {
        docenteService.deleteDocente(id);
    }

    @PutMapping("/{id}")
    public Docente updateDocente(@PathVariable Long id, @RequestBody Docente docenteInfo) {
        Docente docente = docenteService.getDocenteById(id);

        if (docente != null) {
            docente.setNombre(docenteInfo.getNombre());
            docente.setApellido(docenteInfo.getApellido());
            docente.setEmail(docenteInfo.getEmail());
            return docenteService.updateDocente(docente);
        } else {
            return null;
        }
    }

    @GetMapping("/search")
    public Page<Docente> searchDocentes(
            @RequestParam(required = false) String search,
            @PageableDefault(size = 10, sort = "apellido", direction = Sort.Direction.ASC) Pageable pageable) {

        if (search != null && !search.isEmpty()) {
            return docenteService.searchByNameOrSurname(search, pageable);
        } else {
            return docenteService.getAllDocentes(pageable);  // Si no hay filtros, devolver todos
        }
    }

}
