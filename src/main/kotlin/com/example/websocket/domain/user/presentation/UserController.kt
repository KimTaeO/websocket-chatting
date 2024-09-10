package com.example.websocket.domain.user.presentation

import com.example.websocket.domain.user.presentation.data.request.SignInRequest
import com.example.websocket.domain.user.presentation.data.request.SignUpRequest
import com.example.websocket.domain.user.presentation.data.response.TokenResponse
import com.example.websocket.domain.user.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService
) {
    @PostMapping("/signup")
    fun signUp(@Valid @RequestBody signUpRequest: SignUpRequest): ResponseEntity<Unit> {
        userService.signUp(signUpRequest)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PostMapping("/signin")
    fun signIn(@Valid @RequestBody signInRequest: SignInRequest): ResponseEntity<TokenResponse> {
        val response = userService.signIn(signInRequest)
        return ResponseEntity.status(HttpStatus.OK).body(response)
    }
}