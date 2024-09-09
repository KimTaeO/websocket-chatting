package com.example.websocket.domain.chatroom.entity

import com.example.websocket.domain.user.entity.User
import javax.persistence.*

@Entity
class Message(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column
    val message: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_id", nullable = false)
    val chatRoom: ChatRoom
)