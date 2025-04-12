package src.main.dao;

import java.util.List;

public interface Repository<T> {
    void insert(T t);
    boolean delete(int id);
    List<T> getAll();
    void update(T t);
    T getById(int id);
}
