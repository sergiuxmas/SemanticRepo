package com.semanticrepo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ProjectConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        /*
        Spring stores the password string literally: "{noop}12345".
        During login, Spring Security calls the configured PasswordEncoder
        DelegatingPasswordEncoder looks at the prefix {noop} and picks the NoOp encoder.
        NoOp encoder means:
            “encoded password” is just "12345"
            it matches the raw password "12345" by simple string compare
            So login with 12345 succeeds
         */
        var userDetailsService = new InMemoryUserDetailsManager();
        var user = User.withUsername("john")
                .password("{noop}12345")
                .authorities("read")
                .build();
        userDetailsService.createUser(user);
        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
