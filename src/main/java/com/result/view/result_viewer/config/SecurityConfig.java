package com.result.view.result_viewer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http ) throws Exception{

        //http security ka use krke, request ko configure kr skte hai--

//      http.authorizeHttpRequests(httpRequest ->{
//
//          httpRequest.requestMatchers("/admin/add_result").authenticated();
//          httpRequest.requestMatchers("/admin/add_result_action").authenticated();
//          httpRequest.anyRequest().permitAll();
//      });

      //Different Way---

//        http.csrf(e -> e.disable());
        http.authorizeHttpRequests(httpRequests ->
                httpRequests.requestMatchers("/admin/add_result").authenticated()
                        .requestMatchers("/admin/add_result_action").authenticated()
                        .anyRequest().permitAll()
                )
                //.formLogin(Customizer.withDefaults());
                .formLogin(formLogin ->
                        formLogin.loginPage("/user_login")
                                .loginProcessingUrl("/do_login")
                                .successForwardUrl("/admin/result_page")
                                .permitAll()
                ).logout(logout ->
                        logout.logoutUrl("/user_logout")
                                .logoutSuccessUrl("/user_login?logout")
                                .permitAll()
                );


        DefaultSecurityFilterChain build = http.build();

        return build;

    }
}
