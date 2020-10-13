package com.codegym.service.impl;

import com.codegym.model.Country;
import com.codegym.model.Landscape;
import com.codegym.repository.LandscapeRepository;
import com.codegym.service.ILandscapeService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LandscapeServiceImpl implements ILandscapeService {
    @Autowired
    private LandscapeRepository landscapeRepository;

    @Override
    public Page<Landscape> findAll(Pageable pageable) {
        return landscapeRepository.findAll(pageable);
    }

    @Override
    public Landscape findById(Long id) {
        return landscapeRepository.findOne(id);
    }

    @Override
    public void save(Landscape landscape) {
        landscapeRepository.save(landscape);
    }

    @Override
    public void remove(Long id) {
        landscapeRepository.delete(id);
    }

    @Override
    public Iterable<Landscape> findAllByCountry(Country country) {
        return landscapeRepository.findAllByCountry(country);
    }

    @Override
    public Page<Landscape> findAllByNameContaining(String name, Pageable pageable) {
        return landscapeRepository.findByNameContaining(name,pageable);
    }
}
