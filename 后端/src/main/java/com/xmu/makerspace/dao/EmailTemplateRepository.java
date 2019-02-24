package com.xmu.makerspace.dao;

import com.xmu.makerspace.domain.EmailTemplate;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface EmailTemplateRepository extends CrudRepository<EmailTemplate,Integer> {
    ArrayList<EmailTemplate> findAll();
}
