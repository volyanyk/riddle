package dev.volyanyk.riddle.model

import java.util.UUID

data class Message (
    val id: UUID = UUID.randomUUID(),
    val message: String,
    val ttl: Int,
    val accessCode: Int
)