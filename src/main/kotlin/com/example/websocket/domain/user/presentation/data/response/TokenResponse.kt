package com.example.websocket.domain.user.presentation.data.response

import com.example.websocket.domain.user.enum.Authority
import java.time.LocalDateTime

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String,
    val accessExpiredAt: LocalDateTime,
    val refreshExpiredAt: LocalDateTime,
    val authority: Authority
)