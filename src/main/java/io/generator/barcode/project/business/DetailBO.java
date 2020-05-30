package io.generator.barcode.project.business;

import io.generator.barcode.project.entity.Details;
import java.util.List;

public interface DetailBO {
    void save (Details data);
    void update(Details data);
    void delete(String data);
    List<Details> findAll();
    List<Details> searchData(String data);
}
