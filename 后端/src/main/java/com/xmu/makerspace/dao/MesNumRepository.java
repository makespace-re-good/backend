package com.xmu.makerspace.dao;

import com.xmu.makerspace.domain.MesNum;
import org.springframework.data.repository.CrudRepository;

public interface MesNumRepository extends CrudRepository<MesNum,Integer> {
    MesNum findAllByTeamId(int team_id);
}
