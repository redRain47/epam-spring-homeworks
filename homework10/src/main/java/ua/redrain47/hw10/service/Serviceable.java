package ua.redrain47.hw10.service;

import java.util.List;

public interface Serviceable<T, ID> {
    T getById(ID id);

    List<T> getAll();

    void create(T dto);

    void update(T dto);

    void deleteById(ID id);
}
