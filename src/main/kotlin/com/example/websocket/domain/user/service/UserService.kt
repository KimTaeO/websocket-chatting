package com.example.websocket.domain.user.service

import com.example.websocket.domain.user.entity.User
import com.example.websocket.domain.user.enum.Authority
import com.example.websocket.domain.user.presentation.data.request.SignInRequest
import com.example.websocket.domain.user.presentation.data.request.SignUpRequest
import com.example.websocket.domain.user.presentation.data.response.TokenResponse
import com.example.websocket.domain.user.repository.UserRepository
import com.example.websocket.global.security.jwt.JwtTokenGenerator
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class UserService(
    private val userRepository: UserRepository,
    private val jwtTokenGenerator: JwtTokenGenerator
) {
    @Transactional(rollbackFor = [Exception::class])
    fun signUp(signUpRequest: SignUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.email))
            throw RuntimeException("존재하는 이메일입니다")

        val user = User(
            id = UUID.randomUUID(),
            name = signUpRequest.name,
            email = signUpRequest.email,
            password = signUpRequest.password,
            authority = Authority.USER
        )

        userRepository.save(user)
    }

    @Transactional(readOnly = true)
    fun signIn(signInRequest: SignInRequest): TokenResponse {
        val user = userRepository.findByEmail(signInRequest.email) ?: throw RuntimeException("해당하는 유저를 찾을 수 없습니다")

        if(user.password != signInRequest.password)
            throw RuntimeException("비밀번호가 일치하지 않습니다")

        return jwtTokenGenerator.generateToken(user.id, Authority.USER)
    }
}