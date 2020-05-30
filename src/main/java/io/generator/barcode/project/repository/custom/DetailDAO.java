package io.generator.barcode.project.repository.custom;

import io.generator.barcode.project.entity.Details;
import io.generator.barcode.project.repository.CrudDAO;

import java.util.List;

public interface DetailDAO extends CrudDAO<Details,String> {

    List<Details> search(String data);
}
