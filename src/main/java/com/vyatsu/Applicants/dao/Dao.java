package com.vyatsu.Applicants.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Optional<T> get(long id);

    int size();

    List<T> getAll();
    void save(T t);
    void delete(T t);
}
