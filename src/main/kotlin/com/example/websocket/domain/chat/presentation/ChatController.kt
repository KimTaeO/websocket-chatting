package com.example.websocket.domain.chat.presentation

import com.example.websocket.domain.chat.presentation.dto.ChatMessageDto
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.web.bind.annotation.RestController

@RestController
class ChatController(
    private val simpMessageSendingOperations: SimpMessageSendingOperations
) {
    @MessageMapping("/hello")
    fun sendMessage(chatMessageDto: ChatMessageDto) {
        simpMessageSendingOperations.convertAndSend("/sub", chatMessageDto)
    }
}