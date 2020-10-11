package com.codegym.service;

import java.util.List;

public interface IService<T> {
    Iterable<T> findAll();

    T findById(Long id);

    T update(T model);

    T save(T model);

    void remove(Long id);
}
