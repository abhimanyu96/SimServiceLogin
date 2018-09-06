//package com.example.demo.security;
//
//import org.springframework.boot.autoconfigure.security.SecurityProperties;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//
//@Configuration
//@EnableWebSecurity
//@Order(SecurityProperties.BASIC_AUTH_ORDER)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//
//		http.csrf().disable();
//
//		http.authorizeRequests()
//
//        .antMatchers("/").fullyAuthenticated()
//
//        .and().formLogin().defaultSuccessUrl("/").permitAll()
//
//        .and().logout().permitAll().and().csrf();
//        http.csrf().disable();
//
//		
//	}
//}
