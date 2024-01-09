package dev.jesusdlc.cartrack.config.security;

import dev.jesusdlc.cartrack.config.security.handler.CustomAccessDeniedHandler;
import dev.jesusdlc.cartrack.config.security.handler.CustomAuthenticationEntryPoint;
import dev.jesusdlc.cartrack.domain.util.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
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

                    auth.requestMatchers(HttpMethod.GET,"/api/cars").hasAnyRole(Role.ADMINISTRATOR.name(),Role.ASSISTANT.name());
                    auth.requestMatchers(HttpMethod.GET,"/api/cars/{id}").hasAnyRole(Role.ADMINISTRATOR.name(),Role.ASSISTANT.name());
                    auth.requestMatchers(HttpMethod.POST,"/api/cars").hasRole(Role.ADMINISTRATOR.name());
                    auth.requestMatchers(HttpMethod.PATCH,"/api/cars").hasAnyRole(Role.ADMINISTRATOR.name(),Role.ASSISTANT.name());
                    auth.requestMatchers(HttpMethod.DELETE,"/api/cars/{id}").hasRole(Role.ADMINISTRATOR.name());

                    auth.requestMatchers(HttpMethod.GET,"/api/brand").hasAnyRole(Role.ADMINISTRATOR.name(),Role.ASSISTANT.name());
                    auth.requestMatchers(HttpMethod.GET,"/api/brand/{id}").hasAnyRole(Role.ADMINISTRATOR.name(),Role.ASSISTANT.name());
                    auth.requestMatchers(HttpMethod.POST,"/api/brand").hasRole(Role.ADMINISTRATOR.name());
                    auth.requestMatchers(HttpMethod.PATCH,"/api/brand").hasAnyRole(Role.ADMINISTRATOR.name(),Role.ASSISTANT.name());
                    auth.requestMatchers(HttpMethod.DELETE,"/api/brand/{id}").hasRole(Role.ADMINISTRATOR.name());

                    auth.requestMatchers(HttpMethod.POST,"/api/user/**").permitAll();
                    auth.anyRequest().authenticated();
                })
                .build();
    }

}
