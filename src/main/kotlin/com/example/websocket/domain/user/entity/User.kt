package com.example.websocket.domain.user.entity

import com.example.websocket.domain.user.enum.Authority
import javax.persistence.*

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column
    val name: String,

    @Column
    val email: String,

    @Column
    val password: String,

    @Enumerated(EnumType.STRING)
    val authority: Authority
)