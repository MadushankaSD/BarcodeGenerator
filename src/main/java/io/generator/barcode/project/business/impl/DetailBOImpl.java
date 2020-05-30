package io.generator.barcode.project.business.impl;

import io.generator.barcode.project.business.DetailBO;
import io.generator.barcode.project.entity.Details;
import io.generator.barcode.project.repository.custom.DetailDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class DetailBOImpl implements DetailBO {

    @Autowired
    DetailDAO detail;

    @Override
    public void save(Details data) {
        detail.save(data);
    }

    @Override
    public void update(Details data) {
        detail.update(data);
    }

    @Override
    public void delete(String data) {
        detail.delete(data);
    }

    @Override
    public List<Details> findAll() {
      return   detail.findAll();
    }

    @Override
    public List<Details> searchData(String data) {
        return detail.search(data);
    }
}
