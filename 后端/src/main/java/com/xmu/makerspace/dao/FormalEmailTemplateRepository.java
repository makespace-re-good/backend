package com.xmu.makerspace.dao;

import com.xmu.makerspace.domain.FormalEmailTemplate;
import org.springframework.data.repository.CrudRepository;

public interface FormalEmailTemplateRepository extends CrudRepository<FormalEmailTemplate,Integer> {
    FormalEmailTemplate findById(int id);
}
