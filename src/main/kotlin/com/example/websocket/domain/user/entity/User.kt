package com.example.websocket.domain.user.entity

import com.example.websocket.domain.user.enum.Authority
import java.util.UUID
import javax.persistence.*

@Entity
class User(
    @Id
    val id: UUID,

    @Column
    val name: String,

    @Column
    val email: String,

    @Column
    val password: String,

    @Enumerated(EnumType.STRING)
    val authority: Authority
)