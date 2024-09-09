package com.example.websocket.global.security.jwt

import com.example.websocket.domain.user.entity.RefreshToken
import com.example.websocket.domain.user.enum.Authority
import com.example.websocket.domain.user.presentation.data.response.TokenResponse
import com.example.websocket.domain.user.repository.RefreshTokenRepository
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import com.example.websocket.global.security.jwt.properties.JwtProperties
import java.security.Key
import java.time.LocalDateTime
import java.util.*

@Component
class JwtTokenGenerator(
    private val jwtProperties: JwtProperties,
    private val refreshTokenRepository: RefreshTokenRepository
) {
    fun generateToken(userId: UUID, authority: Authority): TokenResponse {
        val accessToken = generateAccessToken(userId, jwtProperties.accessSecret, authority)
        val refreshToken = generateRefreshToken(userId, jwtProperties.refreshSecret, authority)
        val accessExpiredAt = getAccessTokenExpiredAt()
        val refreshExpiredAt = getRefreshTokenExpiredAt()
        refreshTokenRepository.save(RefreshToken(refreshToken, userId, authority, jwtProperties.refreshExpiration))
        return TokenResponse(accessToken, refreshToken, accessExpiredAt, refreshExpiredAt, authority)
    }

    private fun generateAccessToken(userId: UUID, secret: Key, authority: Authority): String =
        Jwts.builder()
            .signWith(secret, SignatureAlgorithm.HS256)
            .setSubject(userId.toString())
            .claim("type", JwtProperties.accessType)
            .claim(JwtProperties.roleType, authority)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtProperties.accessExpiration * 1000))
            .compact()

    private fun generateRefreshToken(userId: UUID, secret: Key, authority: Authority): String =
        Jwts.builder()
            .signWith(secret, SignatureAlgorithm.HS256)
            .setSubject(userId.toString())
            .claim("type", JwtProperties.refreshType)
            .claim(JwtProperties.roleType, authority)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + jwtProperties.refreshExpiration * 1000))
            .compact()

    private fun getAccessTokenExpiredAt(): LocalDateTime =
        LocalDateTime.now().plusSeconds(jwtProperties.accessExpiration.toLong())

    private fun getRefreshTokenExpiredAt(): LocalDateTime =
        LocalDateTime.now().plusSeconds(jwtProperties.refreshExpiration.toLong())
}