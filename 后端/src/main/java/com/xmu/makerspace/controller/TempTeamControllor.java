/**
 * 扩展临时团队的接口
 */


package com.xmu.makerspace.controller;

import com.xmu.makerspace.dao.TempTeamRepository;
import com.xmu.makerspace.service.TempTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("tempTeam")
public class TempTeamControllor {

    @Autowired
    TempTeamRepository tempTeamRepository;
    @Autowired
    TempTeamService tempTeamService;

    /**
     * 跳转到确认页面
     * @param teamid
     * @param httpServletRequest
     * @param httpServletResponse
     */
    @RequestMapping("/{teamid}")
    public void toConfirmUrl(@PathVariable("teamid")String teamid, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
    {
         tempTeamService.toConfirmUrl(httpServletResponse,httpServletRequest,teamid);
    }

    /**
     * 正式确认团队入驻
     * @param httpServletRequest
     * @return
     * @throws MessagingException
     */
    @RequestMapping("confirmEnter")
    public String confirmEnter(HttpServletRequest httpServletRequest) throws MessagingException {
        return tempTeamService.confirmEnter(httpServletRequest);
    }

    @RequestMapping("getTeamName")
    public String getTeamName(HttpServletRequest httpServletRequest)
    {
        String team_id = "";
        Cookie[] cookies = httpServletRequest.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("teamid")) {
                team_id = cookies[i].getValue();
                break;
            }
        }
        String teamName="";
        teamName=tempTeamRepository.findByTeamId(team_id).getTeamName();
        return teamName;
    }
}
