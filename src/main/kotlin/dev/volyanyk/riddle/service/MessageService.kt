package dev.volyanyk.riddle.service

import com.github.benmanes.caffeine.cache.Cache
import dev.volyanyk.riddle.config.CaffeineConfig
import dev.volyanyk.riddle.model.Message
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheConfig
import java.util.UUID


@CacheConfig(cacheNames = ["messages"])
open class MessageService {
    fun create(message: Message): UUID {


        TODO("Not yet implemented")
    }

    fun getMessage(id: UUID): Message? {
        TODO("Not yet implemented")
    }
}