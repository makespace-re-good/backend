package com.xmu.makerspace.config.interceptor;

import com.xmu.makerspace.dao.FormalTeamRepository;
import com.xmu.makerspace.domain.AuditStep;
import com.xmu.makerspace.domain.FormalTeam;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by status200 on 2017/9/18.
 */
@Component
public class SetTeamAccountInterceptor extends HandlerInterceptorAdapter{

    private final FormalTeamRepository formalTeamRepository;

    public SetTeamAccountInterceptor(FormalTeamRepository formalTeamRepository) {
        this.formalTeamRepository = formalTeamRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String teamId = (String) pathVariables.get("teamId");

        FormalTeam formalTeam = formalTeamRepository.findOne(Integer.parseInt(teamId));

        if(formalTeam != null && formalTeam.getAuditStep() != AuditStep.NOT_SET_ACCOUNT) {
            response.sendRedirect("/404");
            return false;
        }

        return true;


    }
}
