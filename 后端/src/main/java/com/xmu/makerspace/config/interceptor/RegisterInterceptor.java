package com.xmu.makerspace.config.interceptor;

import com.xmu.makerspace.service.ApplicationRoundCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by status200 on 2017/8/31.
 */
@Component
public class RegisterInterceptor extends HandlerInterceptorAdapter {


    private final ApplicationRoundCheckService applicationRoundCheckService;

    public RegisterInterceptor(ApplicationRoundCheckService applicationRoundCheckService) {
        this.applicationRoundCheckService = applicationRoundCheckService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!applicationRoundCheckService.isApplicationOpen()) {
            response.sendRedirect("/application-not-open");
            return false;
        }

        return true;
    }
}
