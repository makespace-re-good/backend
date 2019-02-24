package com.xmu.makerspace.dao;

import com.xmu.makerspace.domain.Request;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface RequestRepository extends CrudRepository<Request,String> {
    ArrayList<Request> findAllByTeamId(String team_id);
    ArrayList<Request> findAll();
}
