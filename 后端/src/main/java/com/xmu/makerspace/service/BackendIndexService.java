package com.xmu.makerspace.service;

import com.xmu.makerspace.dao.ApplicationRoundRepository;
import com.xmu.makerspace.dao.TempTeamRepository;
import com.xmu.makerspace.domain.ApplicationRound;
import com.xmu.makerspace.model.BackendIndexVO;
import com.xmu.makerspace.model.NewApplicationRoundDTO;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by status200 on 2017/8/30.
 */
@Service
public class BackendIndexService {

    private final ApplicationRoundRepository applicationRoundRepository;

    private final TempTeamRepository tempTeamRepository;

    private final TempTeamFileService tempTeamFileService;
    @Autowired
    public BackendIndexService(ApplicationRoundRepository applicationRoundRepository, TempTeamRepository tempTeamRepository, TempTeamFileService tempTeamFileService) {
        this.applicationRoundRepository = applicationRoundRepository;
        this.tempTeamRepository = tempTeamRepository;
        this.tempTeamFileService = tempTeamFileService;
    }

    /**
     * 获取当前入驻、期数等统计信息
     *
     * @return
     */
    @Transactional(readOnly = true)
    public Map<String,Object> getStatistic() {
        HashMap<String, Object> statistic = new HashMap<>();

        List<ApplicationRound> applicationRounds = applicationRoundRepository.findFirstByOrderByBeginDateDesc();

        if(applicationRounds.size() == 0) {
            statistic.put("applicationRound",null);
        } else {
            // 获取最新一期的申请信息
            ApplicationRound currentApplication = applicationRounds.get(0);

            BackendIndexVO vo = new BackendIndexVO();
            // 设置vo
            vo.setBeginDate(currentApplication.getBeginDate());
            vo.setEndDate(currentApplication.getEndDate());
            vo.setTermId(currentApplication.getTermId());

            vo.setTeamNumber((short) tempTeamRepository.count());

            statistic.put("applicationRound", vo);
        }

        return statistic;
    }

    /**
     * 创建新一期的申请
     */
    @Transactional(rollbackFor = Exception.class)
    public void createNewApplicationRound(NewApplicationRoundDTO dto) throws IOException {
        // 删除所有之前的临时申请
        tempTeamRepository.deleteAll();
        // 删除所有临时团队的文件
        tempTeamFileService.deleteAllTempTeamFiles();

        applicationRoundRepository.save(applicationRoundDTO2Domain(dto));
    }

    /**
     * 将NewApplicationRoundDTO转成ApplicationRound
     *
     * @param dto 需要转换的DTO对象
     * @return
     */
    public static ApplicationRound applicationRoundDTO2Domain(NewApplicationRoundDTO dto) {
        ApplicationRound applicationRound = new ApplicationRound();

        applicationRound.setBeginDate(dto.getBeginDate());
        applicationRound.setEndDate(dto.getEndDate());
        applicationRound.setTermId((short)dto.getTermId());
        applicationRound.setTeamNumber((short)0);

        return applicationRound;
    }

    /**
     * 关闭当前的申请
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean closeCurrentApplicationRound() {
        try {
            applicationRoundRepository.deleteAll();
        }catch (Exception e)
        {
            return false;
        }
        return true;
    }

    /**
     * 验证期号是否存在
     */
    @Transactional(readOnly = true)
    public Map<String,Boolean> validateTermId(int termId) {
        Map<String, Boolean> result = new HashMap<>();
        result.put("exists",applicationRoundRepository.exists(termId));

        return result;
    }



}
