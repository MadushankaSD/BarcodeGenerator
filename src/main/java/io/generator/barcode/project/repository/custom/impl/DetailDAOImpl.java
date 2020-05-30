package io.generator.barcode.project.repository.custom.impl;

import io.generator.barcode.project.entity.Details;
import io.generator.barcode.project.repository.CrudDAOImpl;
import io.generator.barcode.project.repository.custom.DetailDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DetailDAOImpl extends CrudDAOImpl<Details,String> implements DetailDAO {

    @Override
    public List<Details> search(String data) {
      return   entityManager.createQuery("SELECT D FROM Details D WHERE D.itemCode LIKE ?1 OR D.Name LIKE ?1",Details.class).setParameter(1,data).getResultList();
    }
}
