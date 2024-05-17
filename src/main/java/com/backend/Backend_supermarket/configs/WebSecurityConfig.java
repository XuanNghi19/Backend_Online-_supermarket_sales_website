package com.backend.Backend_supermarket.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.backend.Backend_supermarket.enums.Role;
import com.backend.Backend_supermarket.filters.JwtTokenFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtTokenFilter jwtTokenFilter;

    @Value("${api.prefix}")
    private String apiPrefix;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(request -> {
                    request
                        .requestMatchers(
                                HttpMethod.GET,
                                String.format("%s/users/avatar/**", apiPrefix),
                                String.format("%s/products/**", apiPrefix),
                                String.format("%s/productImages/**", apiPrefix),
                                String.format("%s/products", apiPrefix),
                                String.format("%s/categories", apiPrefix))
                        .permitAll()
                        .requestMatchers(
                                "/api-docs",
                                "/api-docs/**",
                                "/swagger-resources",
                                "/swagger-resources/**",
                                "/configuration/ui",
                                "/configuration/security",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/webjars/swagger-ui/**",
                                "/swagger-ui/index.html")
                        .permitAll()
                        .requestMatchers(
                                HttpMethod.POST,
                                String.format("%s/users/register", apiPrefix),
                                String.format("%s/users/login", apiPrefix))
                        .permitAll()
//////////////////////////////////////////////////////////////////////////////////////////////////
                        .requestMatchers(
                                HttpMethod.GET,
                                String.format("%s/users/**", apiPrefix))
                        .hasAnyRole(Role.USER.toString(), Role.ADMIN.toString(), Role.WAREHOUSE.toString(), Role.SALES.toString())
                        .requestMatchers(
                                HttpMethod.PUT,
                                String.format("%s/users/**", apiPrefix))
                        .hasAnyRole(Role.USER.toString(), Role.ADMIN.toString(), Role.WAREHOUSE.toString(), Role.SALES.toString())
                        .requestMatchers(
                                HttpMethod.POST,
                                String.format("%s/users/**", apiPrefix),
                                String.format("%s/users/uploads", apiPrefix))
                        .hasAnyRole(Role.USER.toString(), Role.ADMIN.toString(), Role.WAREHOUSE.toString(), Role.SALES.toString())
//////////////////////////////////////////////////////////////////////////////////////////////////
                        // USER
                        .requestMatchers(
                                HttpMethod.GET,
                                String.format("%s/orders/**", apiPrefix))
                        .hasAnyRole(Role.USER.toString())
                        .requestMatchers(
                                HttpMethod.POST,
                                String.format("%s/comments", apiPrefix),
                                String.format("%s/orders", apiPrefix))
                        .hasRole(Role.USER.toString())
                        .requestMatchers(
                                HttpMethod.PUT,
                                String.format("%s/comments/**", apiPrefix))
                        .hasRole(Role.USER.toString())
                        .requestMatchers(
                                HttpMethod.DELETE,
                                String.format("%s/comments/**", apiPrefix))
                        .hasRole(Role.USER.toString())
//////////////////////////////////////////////////////////////////////////////////////////////////
                        // ADMIN
                        .requestMatchers(
                                HttpMethod.GET,
                                String.format("%s/managementUser/**", apiPrefix),
                                String.format("%s/partners/**", apiPrefix))
                        .hasAnyRole(Role.ADMIN.toString())
                        .requestMatchers(
                                HttpMethod.PUT,
                                String.format("%s/managementUser/**", apiPrefix),
                                String.format("%s/partners/**", apiPrefix))
                        .hasAnyRole(Role.ADMIN.toString())
                        .requestMatchers(
                                HttpMethod.POST,
                                String.format("%s/managementUser/**", apiPrefix),
                                String.format("%s/partners/**", apiPrefix))
                        .hasAnyRole(Role.ADMIN.toString())
                        .requestMatchers(
                                HttpMethod.DELETE,
                                String.format("%s/managementUser/**", apiPrefix),
                                String.format("%s/partners/**", apiPrefix))
                        .hasAnyRole(Role.ADMIN.toString())
//////////////////////////////////////////////////////////////////////////////////////////////////
                        // WAREHOUSE
                        .requestMatchers(
                                HttpMethod.GET,
                                String.format("%s/inventoryCheck/**", apiPrefix),
                                String.format("%s/receipts/**", apiPrefix))
                        .hasAnyRole(Role.WAREHOUSE.toString(), Role.ADMIN.toString())
                        .requestMatchers(
                                HttpMethod.PUT,
                                String.format("%s/inventoryCheck/**", apiPrefix),
                                String.format("%s/receipts/**", apiPrefix))
                        .hasAnyRole(Role.WAREHOUSE.toString(), Role.ADMIN.toString())
                        .requestMatchers(
                                HttpMethod.POST,
                                String.format("%s/inventoryCheck/**", apiPrefix),
                                String.format("%s/receipts/**", apiPrefix))
                        .hasAnyRole(Role.WAREHOUSE.toString(), Role.ADMIN.toString())
                        .requestMatchers(
                                HttpMethod.DELETE,
                                String.format("%s/inventoryCheck/**", apiPrefix),
                                String.format("%s/receipts/**", apiPrefix))
                        .hasAnyRole(Role.WAREHOUSE.toString(), Role.ADMIN.toString())
//////////////////////////////////////////////////////////////////////////////////////////////////
                        // SALES
                        .requestMatchers(
                                HttpMethod.GET,
                                String.format("%s/receipts/**", apiPrefix))
                        .hasAnyRole(Role.SALES.toString(), Role.ADMIN.toString())
                        .requestMatchers(
                                HttpMethod.PUT)
                        .hasAnyRole(Role.SALES.toString(), Role.ADMIN.toString())
                        .requestMatchers(
                                HttpMethod.POST)
                        .hasAnyRole(Role.SALES.toString(), Role.ADMIN.toString())
                        .requestMatchers(
                                HttpMethod.DELETE)
                        .hasAnyRole(Role.SALES.toString(), Role.ADMIN.toString())
                        .anyRequest().authenticated();
                });
        return http.build();
    }
}
