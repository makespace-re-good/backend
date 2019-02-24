package com.xmu.makerspace.config;

import com.xmu.makerspace.config.interceptor.RegisterInterceptor;
import com.xmu.makerspace.config.interceptor.SetTeamAccountInterceptor;
import com.xmu.makerspace.config.interceptor.UpdateRegisterInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by status200 on 2017/7/30.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter{

    // 由于该拦截器中需要查询数据库,因此必须将其作为一个Bean,让Spring管理其生命周期
    private final UpdateRegisterInterceptor updateRegisterInterceptor;

    private final RegisterInterceptor registerInterceptor;

    private final SetTeamAccountInterceptor setTeamAccountInterceptor;

    public MvcConfig(UpdateRegisterInterceptor updateRegisterInterceptor,
                     RegisterInterceptor registerInterceptor,
                     SetTeamAccountInterceptor setTeamAccountInterceptor) {
        this.updateRegisterInterceptor = updateRegisterInterceptor;
        this.registerInterceptor = registerInterceptor;
        this.setTeamAccountInterceptor = setTeamAccountInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 添加进入修改申请页的拦截器
        registry.addInterceptor(updateRegisterInterceptor).addPathPatterns("/update-register/**");
        registry.addInterceptor(registerInterceptor).addPathPatterns("/register");
        registry.addInterceptor(setTeamAccountInterceptor).addPathPatterns("/set-team-account/**");
        super.addInterceptors(registry);
    }



}
