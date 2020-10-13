package com.codegym.service;

import com.codegym.model.Country;

public interface ICountryService {
    Iterable<Country> findAll();
    Country findById(Long id);
    void save(Country country);
    void remove(Long id);
}
