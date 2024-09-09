package com.example.websocket.domain.user.entity

import com.example.websocket.domain.user.enum.Authority
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import java.util.*
import java.util.concurrent.TimeUnit

@RedisHash("refresh_token")
data class RefreshToken(
    @Id
    val token: String,

    val userId: UUID,

    val authority: Authority,

    @TimeToLive(unit = TimeUnit.SECONDS)
    val expiredAt: Int
)