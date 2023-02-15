**Migration to Spring boot 3.0.2**

**Change your pom.xml:**

1. Delete all dependencies that contains hibernate
2. Add this dependency:
   <dependency>
       <groupId>org.thymeleaf.extras</groupId>
       <artifactId>thymeleaf-extras-springsecurity6</artifactId>
       <!-- Temporary explicit version to fix Thymeleaf bug -->
       <version>3.1.1.RELEASE</version>
   </dependency>

**Change To jakarta:**
Spring Boot 3 is based on Jakarta, therefor, you need to change javax to jakarta in your importation

**Replace the SecurityConfig.java by:**

    import jakarta.servlet.ServletException;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.context.annotation.Profile;
    import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
    import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
    import org.springframework.security.core.Authentication;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
    import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
    import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
    import java.io.IOException;
    
    @Configuration
    @EnableWebSecurity
    @Profile({"dev","test"})
    @EnableMethodSecurity
    @EnableRedisHttpSession
    public class SecurityConfig{

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("ram").password("ram123").authorities("Banques", "test");
        auth.inMemoryAuthentication().withUser("nihel").password("123").authorities("z");
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin().loginProcessingUrl("/login").successHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            }
        });
        http.csrf().disable();
        http.headers().frameOptions().disable();
        return http.build();

    }

    @Bean
    HeaderHttpSessionIdResolver sessionStrategy() {
        return HeaderHttpSessionIdResolver.xAuthToken();
    }
}

**Replace the SecurityConfigProd.java by:**

    import io.lettuce.core.dynamic.annotation.Value;
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
    
    @Configuration
    @EnableWebSecurity
    @Profile("prod")
    @EnableRedisHttpSession
    @EnableMethodSecurity(prePostEnabled = true)
    public class SecurityConfigProd{

    private final Environment env;

    @Value("${spring.redis.host}")
    private String hostName;

    public SecurityConfigProd(Environment env) {
        this.env = env;
    }
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration =
                new RedisStandaloneConfiguration(hostName);
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        if (env.acceptsProfiles(Profiles.of("unsecure"))) {
            http.authorizeHttpRequests().requestMatchers(HttpMethod.DELETE, "/api/**").authenticated();
            http.authorizeHttpRequests().requestMatchers(HttpMethod.POST, "/api/**").authenticated();
            http.authorizeHttpRequests().requestMatchers(HttpMethod.PUT, "/api/**").authenticated();
            http.authorizeHttpRequests().requestMatchers(HttpMethod.GET, "/api/**").authenticated();
        } 
        return http.build();
    }

