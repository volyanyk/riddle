package dev.volyanyk.riddle.dto


data class MessageDto (
    val message: String,
    val ttl: Int,
    val accessCode: Int
)