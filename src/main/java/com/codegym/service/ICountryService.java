package com.codegym.service;

import com.codegym.model.Country;
import com.codegym.service.exception.DuplicateCountryException;

public interface ICountryService {
    Iterable<Country> findAll();
    Country findById(Long id);
    void save(Country country) throws DuplicateCountryException;
    void remove(Long id);
}
