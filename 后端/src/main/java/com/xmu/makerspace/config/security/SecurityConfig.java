package com.xmu.makerspace.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by status200 on 2017/9/17.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Configuration
    @Order(1)
    public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter {


        @Override
        protected UserDetailsService userDetailsService() {
            return super.userDetailsService();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth
                    .inMemoryAuthentication()
                    .withUser("admin01")
                    .password("software@110")
                    .roles("ADMIN");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http
                    // 设置页面权限时，必须先调用antMatcher，表明正要对以该字符串开头的url设置权限，此时仍未设置权限
                    .antMatcher("/admin-backend/**")
                    .antMatcher("/api/backend-index/**")
                    // 调用authorizeRequest，表明接下来开始设置权限
                        .authorizeRequests()
                    // 调用antMatchers才正式设置权限

                            .antMatchers("/admin-backend/index").hasRole("ADMIN")
                            .antMatchers("/admin-backend/print-file/**","/teamManagement/**").hasRole("ADMIN")
                            .anyRequest().permitAll()

                    .and()
                        .formLogin()
                            .loginPage("/admin-backend/login")
                            .loginProcessingUrl("/admin-backend/do-login")
                            .defaultSuccessUrl("/admin-backend/index")
                            .failureUrl("/admin-backend/login?error=true")

                    .and()
                        .logout()
                            .logoutUrl("/admin-backend/logout")
                            .logoutSuccessUrl("/")
                            .deleteCookies("JSESSIONID")

                    .and()
                        .exceptionHandling()
                            .accessDeniedPage("/403");
        }
    }

    @Configuration
    @Order(2)
    public static class TeamSecurityConfig extends WebSecurityConfigurerAdapter {

        private final TeamUserDetailsService teamUserDetailsService;

        private final TeamLoginFailureHandler teamLoginFailureHandler;

        public TeamSecurityConfig(TeamUserDetailsService teamUserDetailsService, TeamLoginFailureHandler teamLoginFailureHandler) {
            this.teamUserDetailsService = teamUserDetailsService;
            this.teamLoginFailureHandler = teamLoginFailureHandler;
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {

            DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

            authenticationProvider.setUserDetailsService(teamUserDetailsService);

            // 采用MD5加密
            authenticationProvider.setPasswordEncoder(new Md5PasswordEncoder());

            auth.authenticationProvider(authenticationProvider);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/team-management/**")
                        .authorizeRequests()
                            .antMatchers("/team-management/index").hasRole("TEAM")
                            .anyRequest().permitAll()

                    .and()
                        .formLogin()
                            .loginPage("/team-management/login")
                            .loginProcessingUrl("/team-management/do-login")
                            .defaultSuccessUrl("/team-management/index")
//                            .failureUrl("/team-management/login?error=true")
                            .failureHandler(teamLoginFailureHandler)

                    .and()
                        .logout()
                            .logoutUrl("/team-management/logout")
                            .logoutSuccessUrl("/")
                            .deleteCookies("JSESSIONID")

                    .and()
                        .exceptionHandling()
                            .accessDeniedPage("/403");

        }
    }

    @Configuration
    @Order(3)
    public static class CommonSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/**")
                        .authorizeRequests()
                            .anyRequest().permitAll()
                    .and()
                        .csrf().disable();
        }
    }



}
