package com.csys.template.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;

@Configuration
@EnableWebSecurity
@Profile("prod")
//@EnableRedisHttpSession
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfigProd {

    private final Environment env;

    //@Value("${spring.redis.host}")
    //private String hostName;

    public SecurityConfigProd(Environment env) {
        this.env = env;
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors();
        if (!env.acceptsProfiles(Profiles.of("unsecure"))) {
            http.authorizeHttpRequests().requestMatchers(HttpMethod.DELETE, "/api/**").authenticated();
            http.authorizeHttpRequests().requestMatchers(HttpMethod.POST, "/api/**").authenticated();
            http.authorizeHttpRequests().requestMatchers(HttpMethod.PUT, "/api/**").authenticated();
            http.authorizeHttpRequests().requestMatchers(HttpMethod.GET, "/api/**").authenticated();
        }
        return http.build();
    }

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration =
                new RedisStandaloneConfiguration("integration.clinisys.local/redis",30480);
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public HeaderHttpSessionIdResolver httpSessionIdResolver() {
        return HeaderHttpSessionIdResolver.xAuthToken();
    }

}