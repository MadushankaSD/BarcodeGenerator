package io.generator.barcode.project.repository;


import io.generator.barcode.project.entity.SuperEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class CrudDAOImpl<T extends SuperEntity, ID extends Serializable> implements CrudDAO<T,ID> {

    @PersistenceContext
    protected EntityManager entityManager;
    private Class<T> entity;

    public CrudDAOImpl() {
        entity= (Class<T>)((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments()[0];
    }

    @Override
    public List<T> findAll() {
        return entityManager.createQuery("FROM "+entity.getName()).getResultList();
    }

    @Override
    public T find(ID id) {
        return entityManager.find(entity,id);
    }

    @Override
    public void save(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(T entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(ID id) {
        entityManager.remove(entityManager.getReference(entity,id));
    }
}
