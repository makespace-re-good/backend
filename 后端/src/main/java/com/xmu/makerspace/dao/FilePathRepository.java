package com.xmu.makerspace.dao;

import com.xmu.makerspace.domain.FilePath;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface FilePathRepository extends CrudRepository<FilePath,Integer> {
    FilePath findByTeamid(String teamid);

    @Transactional
    @Modifying
    void deleteByTeamid(String teamid);
}
