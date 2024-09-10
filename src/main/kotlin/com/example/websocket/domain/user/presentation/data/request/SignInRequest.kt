package com.example.websocket.domain.user.presentation.data.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class SignInRequest(
    @field:Email
    val email: String,

    @field:NotBlank
    val password: String
)