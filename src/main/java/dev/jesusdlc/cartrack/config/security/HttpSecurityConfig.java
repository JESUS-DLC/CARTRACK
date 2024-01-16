package dev.jesusdlc.cartrack.config.security;

import dev.jesusdlc.cartrack.config.security.handler.CustomAccessDeniedHandler;
import dev.jesusdlc.cartrack.config.security.handler.CustomAuthenticationEntryPoint;
import dev.jesusdlc.cartrack.domain.util.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class HttpSecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final AuthenticationFilter authenticationFilter;
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;
    private final CustomAccessDeniedHandler accessDeniedHandler;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(excpetion ->{
                    excpetion.authenticationEntryPoint(authenticationEntryPoint);
                    excpetion.accessDeniedHandler(accessDeniedHandler);
                })
                .authorizeHttpRequests(auth ->{

                    auth.requestMatchers(HttpMethod.GET,"/api/cars").hasRole(RoleEnum.CUSTOMER.name());
                    auth.requestMatchers(HttpMethod.GET,"/api/cars/{id}").hasRole(RoleEnum.CUSTOMER.name());
                    auth.requestMatchers(HttpMethod.POST,"/api/cars").hasRole(RoleEnum.CUSTOMER.name());
                    auth.requestMatchers(HttpMethod.PATCH,"/api/cars").hasRole(RoleEnum.CUSTOMER.name());
                    auth.requestMatchers(HttpMethod.DELETE,"/api/cars/{id}").hasRole(RoleEnum.CUSTOMER.name());

                    auth.requestMatchers(HttpMethod.GET,"/api/brand").hasRole(RoleEnum.ADMINISTRATOR.name());
                    auth.requestMatchers(HttpMethod.GET,"/api/brand/{id}").hasRole(RoleEnum.ADMINISTRATOR.name());
                    auth.requestMatchers(HttpMethod.POST,"/api/brand").hasRole(RoleEnum.ADMINISTRATOR.name());
                    auth.requestMatchers(HttpMethod.PATCH,"/api/brand").hasRole(RoleEnum.ADMINISTRATOR.name());
                    auth.requestMatchers(HttpMethod.DELETE,"/api/brand/{id}").hasRole(RoleEnum.ADMINISTRATOR.name());

                    auth.requestMatchers(HttpMethod.POST,"/api/user/**").permitAll();
                    auth.requestMatchers(
                            "/api/v1/auth/**",
                            "/v2/api-docs",
                            "/v3/api-docs",
                            "v3/api-docs/**",
                            "/swagger-resources",
                            "/swagger-resources/**",
                            "/configuration/ui",
                            "/configuration/security",
                            "/swagger-ui/**",
                            "/webjars/**",
                            "/swagger-ui.html"
                    ).permitAll();
                    auth.anyRequest().authenticated();
                })
                .build();
    }

}
