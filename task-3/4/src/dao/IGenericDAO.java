package dao;

import java.util.List;

public interface IGenericDAO<T> {
    void create(T entity);
    T read(int id);
    void update(T entity);
    void delete(T entity);
    List<T> findAll();
}
