package com.tenryu.kpi_management.service;

import com.tenryu.kpi_management.exceptions.ResourceNotFoundException;
import com.tenryu.kpi_management.model.Docente;
import com.tenryu.kpi_management.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DocenteService {

    @Autowired
    private DocenteRepository docenteRepository;

    public Page<Docente> getAllDocentes(Pageable pageable) {
        return docenteRepository.findAll(pageable);
    }

    public Docente getDocenteById(Long id) {
        return docenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Docente no encontrado con el ID: " + id));
    }

    public Docente createDocente(Docente docente) {
        return docenteRepository.save(docente);
    }

    public void deleteDocente(Long id) {
        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Docente no encontrado con el ID: " + id));
        docenteRepository.deleteById(id);
    }

    public Docente updateDocente(Docente docente) {
        return docenteRepository.save(docente);
    }

    public Page<Docente> searchByNameOrSurname(String search, Pageable pageable) {
        return docenteRepository.searchByNameOrSurname(search, pageable);
    }
}