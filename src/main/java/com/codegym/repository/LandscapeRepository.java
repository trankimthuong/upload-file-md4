package com.codegym.repository;

import com.codegym.model.Country;
import com.codegym.model.Landscape;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LandscapeRepository extends PagingAndSortingRepository<Landscape, Long> {
    Iterable<Landscape> findAllByCountry(Country country);

    Page<Landscape> findByNameContaining(String name, Pageable pageable);
}
