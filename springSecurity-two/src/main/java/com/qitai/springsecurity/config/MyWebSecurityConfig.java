package com.qitai.springsecurity.config;

import com.qitai.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
@EnableWebSecurity
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    // 指定密码的加密方式
    @SuppressWarnings("deprecation")
    @Bean
    PasswordEncoder passwordEncoder(){
        // 不对密码进行加密
        return NoOpPasswordEncoder.getInstance();
    }

    // 配置用户及其对应的角色
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    // 配置 URL 访问权限
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(cfisms());
                        object.setAccessDecisionManager(cadm());
                        return object;
                    }
                })
                .and().formLogin().loginProcessingUrl("/login").permitAll()//开启表单登录并配置登录接口
                .and().csrf().disable(); // 关闭csrf
    }

    @Bean
    CustomFilterInvocationSecurityMetadataSource cfisms() {
        return new CustomFilterInvocationSecurityMetadataSource();
    }

    @Bean
    CustomAccessDecisionManager cadm() {
        return new CustomAccessDecisionManager();
    }
}
