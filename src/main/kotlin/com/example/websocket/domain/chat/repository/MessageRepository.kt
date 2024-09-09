package com.example.websocket.domain.chat.repository

import com.example.websocket.domain.chat.entity.Message
import org.springframework.data.repository.CrudRepository

interface MessageRepository: CrudRepository<Message, Long> {
}