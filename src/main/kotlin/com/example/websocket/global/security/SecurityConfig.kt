package com.example.websocket.global.security

import com.example.websocket.global.config.FilterConfig
import com.example.websocket.global.security.jwt.JwtTokenParser
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.RequestMatcher
import org.springframework.web.cors.CorsUtils
import com.example.websocket.global.security.handler.CustomAccessDeniedHandler
import com.example.websocket.global.security.handler.CustomAuthenticationEntryPointHandler

@EnableWebSecurity
class SecurityConfig(
    private val jwtTokenParser: JwtTokenParser
) {
    @Bean
    protected fun filterChain(http: HttpSecurity): SecurityFilterChain =
        http
            .cors()
            .and()
            .csrf().disable()

            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .requestMatchers(RequestMatcher { request ->
                CorsUtils.isPreFlightRequest(request)
            }).permitAll()

            .antMatchers(HttpMethod.POST, "/user/signup").permitAll()
            .antMatchers(HttpMethod.POST, "/user/signin").permitAll()
            .anyRequest().permitAll()

            .and()
            .exceptionHandling()
            .authenticationEntryPoint(CustomAuthenticationEntryPointHandler())
            .accessDeniedHandler(CustomAccessDeniedHandler())

            .and()
            .apply(FilterConfig(jwtTokenParser))
            .and()
            .build()
}