package com.spring.customersspringmvc.services;


import java.util.List;
import java.util.Optional;

public interface IGenerateService <T> {
    List<T> findAll();
    T findById(int id);
    T save(T t);
    void deleteById(int id);
    void update(T t ,int id);
    Boolean existsById(int id);
}
