package ua.redrain47.hw10.repository;

import java.util.List;

public interface GenericRepository<T, ID> {
    void save(T entity);

    T getById(ID id);

    List<T> getAll();

    void deleteById(ID id);
}
