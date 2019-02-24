package com.xmu.makerspace.dao;

import com.xmu.makerspace.domain.TeamEnter;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by status200 on 2017/9/18.
 */
public interface TeamEnterRepository extends CrudRepository<TeamEnter, Integer> {

    TeamEnter findByTeamId(int teamId);
}
