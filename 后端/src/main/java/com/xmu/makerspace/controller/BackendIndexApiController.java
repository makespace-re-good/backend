package com.xmu.makerspace.controller;

import com.xmu.makerspace.model.NewApplicationRoundDTO;
import com.xmu.makerspace.service.BackendIndexService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by status200 on 2017/8/24.
 */

@RestController
@RequestMapping("api/backend-index")
@CrossOrigin
public class BackendIndexApiController {

    private final BackendIndexService backendIndexService;

    private static Log log = LogFactory.getLog(BackendIndexApiController.class);

    @Autowired
    public BackendIndexApiController(BackendIndexService backendIndexService) {
        this.backendIndexService = backendIndexService;
    }

    @GetMapping("get-statistic")
    public Map<String,Object> getStatistic() {
        return backendIndexService.getStatistic();
    }

    /**
     * 发布新的申请
     * @param beginDate
     * @param endDate
     * @param termId
     * @return
     * @throws IOException
     */
    @RequestMapping("create-new-application-round")
    //public String createNewApplicationRound(@RequestBody NewApplicationRoundDTO dto) throws IOException {
      public boolean createNewApplicationRound(String beginDate,String endDate,int termId) throws IOException {

        try {
            Date begin = Date.valueOf(beginDate);
            Date end = Date.valueOf(endDate);
            NewApplicationRoundDTO dto = new NewApplicationRoundDTO(begin, end, termId);
            log.info("creating new application round, data:" + dto);
            //return "hello";
            backendIndexService.createNewApplicationRound(dto);
        }
        catch (Exception e)
        {
            return false;
        }
        return  true;
    }

    //关闭当前发布的申请
    @RequestMapping("close-current-application-round")
    public boolean closeCurrentApplicationRound() {
        //log.info("closing current application round, data:" + data);
        return backendIndexService.closeCurrentApplicationRound();
    }

    @GetMapping("validate-term-id")
    public Map<String,Boolean> validateTermId(@RequestParam("termId") int termId) {
        log.info("validating termId: " + termId);

        return backendIndexService.validateTermId(termId);
    }
}
