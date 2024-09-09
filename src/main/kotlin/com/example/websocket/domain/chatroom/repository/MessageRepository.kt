package com.example.websocket.domain.chatroom.repository

import com.example.websocket.domain.chatroom.entity.Message
import org.springframework.data.repository.CrudRepository

interface MessageRepository: CrudRepository<Message, Long> {
}