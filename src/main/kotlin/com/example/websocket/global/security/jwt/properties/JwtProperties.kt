package com.example.websocket.global.security.jwt.properties

import io.jsonwebtoken.security.Keys
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import java.nio.charset.StandardCharsets
import java.security.Key

@ConstructorBinding
@ConfigurationProperties(prefix = "jwt")
class JwtProperties(
    accessSecret: String,
    refreshSecret: String,
    val accessExpiration: Int,
    val refreshExpiration: Int
) {
    val accessSecret: Key = Keys.hmacShaKeyFor(accessSecret.toByteArray(StandardCharsets.UTF_8))
    val refreshSecret: Key = Keys.hmacShaKeyFor(refreshSecret.toByteArray(StandardCharsets.UTF_8))

    companion object {
        const val tokenPrefix = "Bearer "
        const val tokenHeader = "Authorization"
        const val accessType = "access"
        const val refreshType = "refresh"
        const val roleType = "roleType"
    }
}