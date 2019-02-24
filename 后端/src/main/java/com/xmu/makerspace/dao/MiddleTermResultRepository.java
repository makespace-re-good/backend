package com.xmu.makerspace.dao;

import com.xmu.makerspace.domain.FormalTeam;
import com.xmu.makerspace.domain.MiddleTermResult;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MiddleTermResultRepository extends CrudRepository<MiddleTermResult,Integer> {
    MiddleTermResult findAllByTeamId(int teamId);
}
