package com.spring.boot.project.demo.service;

import java.util.List;

public interface GenericService<T, I> {
    T save(T t);

    List<T> saveAll(List<T> listT);

    T findById(I id);

    List<T> findAll();

    void delete(T t);

    void deleteById(I id);

    void deleteAll();

    void deleteAll(Iterable<T> iterable);
}
