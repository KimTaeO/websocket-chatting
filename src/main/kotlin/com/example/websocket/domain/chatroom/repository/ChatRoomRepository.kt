package com.example.websocket.domain.chatroom.repository

import com.example.websocket.domain.chatroom.entity.ChatRoom
import org.springframework.data.repository.CrudRepository

interface ChatRoomRepository: CrudRepository<ChatRoom, Long> {
}