package com.codegym.service;

import com.codegym.model.Country;
import com.codegym.model.Landscape;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ILandscapeService {
    Page<Landscape> findAll(Pageable pageable);
    Landscape findById(Long id);
    void save(Landscape landscape);
    void remove(Long id);
    Iterable<Landscape> findAllByCountry(Country country);
    Page<Landscape> findAllByNameContaining(String name, Pageable pageable);
}
