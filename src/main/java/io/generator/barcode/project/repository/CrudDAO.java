package io.generator.barcode.project.repository;

import java.util.List;

public interface CrudDAO<T,ID> extends SuperDAO {

    List<T> findAll() ;

    T find(ID id);

    void save(T entity);

    void update(T entity);

    void delete(ID id);
}
