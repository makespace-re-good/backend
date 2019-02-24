package com.xmu.makerspace.config.interceptor;

import com.xmu.makerspace.dao.TempTeamRepository;
import com.xmu.makerspace.service.ApplicationRoundCheckService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 修改申请页面的拦截器.
 * 根据团队id查询团队是否存在.如果团队不存在,则重定向到404页面.
 * 这个拦截器是为了防止在地址栏直接输入团队id造成错误.
 * <p>
 * Created by status200 on 2017/8/11.
 */
@Component
public class UpdateRegisterInterceptor extends HandlerInterceptorAdapter {

    private final TempTeamRepository tempTeamRepository;

    private final ApplicationRoundCheckService applicationRoundCheckService;

    public UpdateRegisterInterceptor(TempTeamRepository tempTeamRepository, ApplicationRoundCheckService applicationRoundCheckService) {
        this.tempTeamRepository = tempTeamRepository;
        this.applicationRoundCheckService = applicationRoundCheckService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {

        // 拿到@PathVariable中定义的变量
        // 方法参考 https://stackoverflow.com/questions/12249721/spring-mvc-3-how-to-get-path-variable-in-an-interceptor
        Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String teamId = (String) pathVariables.get("teamId");


        if(!applicationRoundCheckService.isApplicationOpen()) {
            response.sendRedirect("/application-not-open");
            return false;
        }

        if(!tempTeamRepository.exists(teamId)) {
            response.sendRedirect("/404");
            return false;
        }

        return true;
    }
}
