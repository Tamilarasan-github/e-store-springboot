//package com.tamilcreations.estorespringboot.users;
//
//import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//public class DefaultSecurityConfigurerAdapter extends SecurityConfigurerAdapter<DefaultSecurityConfigurerAdapter, HttpSecurity> {
//
//    private final UserDetailsService userDetailsService;
//    private final PasswordEncoder passwordEncoder;
//
//    public DefaultSecurityConfigurerAdapter(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
//        this.userDetailsService = userDetailsService;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public void init(HttpSecurity http) throws Exception {
//        http
//            .userDetailsService(userDetailsService)
//            .passwordEncoder(passwordEncoder);
//    }
//}
//
