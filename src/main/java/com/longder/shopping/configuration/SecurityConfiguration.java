package com.longder.shopping.configuration;


import com.longder.shopping.security.CustomerUserDetailsService;
import com.longder.shopping.security.FormLoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

/**
 * Spring Security 权限安全配置
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Resource
    private CustomerUserDetailsService userDetailsService;

    /**
     * 配置静态资源部被Security拦截
     *
     * @param web
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static/**","/","/toRegister","/checkLoginName");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                //管理员
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                //登录之后才能看订单类的页面
                .antMatchers("/order/**").authenticated()
                .and()
                .formLogin().loginPage("/toLogin").loginProcessingUrl("/login_check").permitAll()
                .successHandler(formLoginSuccessHandler())
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/")
                .and().csrf().disable().headers().cacheControl();

    }

    /**
     * 配置自定义的UserDetailsService
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * 密码加密工具
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 登陆成功之后的后置跳转
     * @return
     */
    @Bean
    public FormLoginSuccessHandler formLoginSuccessHandler() {
        FormLoginSuccessHandler handler = new FormLoginSuccessHandler();
        handler.setDefaultTargetUrl("/");
        return handler;
    }
}
