package com.example.registerstartupproject.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final RestAuthenticationFailureHandler authenticationFailureHandler;
    private final RestAuthenticationSuccessHandler authenticationSuccessHandler;
    private final String secret;
    private final UserDetailsService userDetailsService;

    public SecurityConfig(RestAuthenticationFailureHandler authenticationFailureHandler, RestAuthenticationSuccessHandler authenticationSuccessHandler,
                          @Value("${jwt.secret}") String secret, UserDetailsService userDetailsService) {
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.secret = secret;
        this.userDetailsService = userDetailsService;
    }

    //    @Bean
//    PasswordEncoder getPasswordEncoder ()
//    {
//        return new BCryptPasswordEncoder();
//    }
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder
                .userDetailsService(userDetailsService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring().antMatchers("/register")
                .mvcMatchers("/validate")
                .mvcMatchers("/resendEmail")
                .mvcMatchers("/insertIdea")
                .mvcMatchers("/h2-console")
                .mvcMatchers("/editUserData")
                .mvcMatchers("/h2-console/**")

//            .mvcMatchers("/**") //DO DEBUGU I TWORZENIA NOWYCH FUNKCJONALNOSCI !

                .mvcMatchers("/contact-form");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
//                .cors(AbstractHttpConfigurer::disable)
                .authorizeRequests()
                .mvcMatchers("/secured").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/register").permitAll()
                .antMatchers(HttpMethod.POST, "/register").permitAll()
                .mvcMatchers("/validate").permitAll()
                .mvcMatchers("/resendEmail").permitAll()
                .mvcMatchers("/insertIdea").permitAll()
                .mvcMatchers("/h2-console").permitAll()
                .mvcMatchers("/editUserData").permitAll()
                .mvcMatchers("/h2-console**").permitAll()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().disable().cors()
                .and()
                .headers().frameOptions().disable()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 1
                .and()
                .addFilter(authenticationFilter())
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userDetailsService, secret)) // 2
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
    }

    @Bean
    public JsonObjectAuthenticationFilter authenticationFilter() throws Exception {
        JsonObjectAuthenticationFilter filter = new JsonObjectAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(authenticationFailureHandler);

        filter.setAuthenticationManager(super.authenticationManager());
        return filter;
    }


}
