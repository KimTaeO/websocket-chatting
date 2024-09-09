package com.example.websocket.domain.chat.repository

import com.example.websocket.domain.chat.entity.ChatRoom
import org.springframework.data.repository.CrudRepository

interface ChatRoomRepository: CrudRepository<ChatRoom, Long> {
}