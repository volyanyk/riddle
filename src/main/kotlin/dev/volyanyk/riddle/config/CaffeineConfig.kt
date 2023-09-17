package dev.volyanyk.riddle.config

import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import com.github.benmanes.caffeine.cache.Expiry
import dev.volyanyk.riddle.model.Message
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.caffeine.CaffeineCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*
import java.util.concurrent.TimeUnit

@Configuration
@EnableCaching
class CaffeineConfig {

    @Bean
    fun caffeineCache(): Cache<UUID, Message> = Caffeine.newBuilder().expireAfter(object : Expiry<UUID, Message> {
            override fun expireAfterCreate(key: UUID, emp: Message, currentTime: Long): Long {
                return TimeUnit.SECONDS.toNanos(emp.ttl.toLong()) * 60
            }

            override fun expireAfterUpdate(key: UUID, emp: Message, currentTime: Long, currentDuration: Long): Long {
                return currentDuration
            }

            override fun expireAfterRead(key: UUID, emp: Message, currentTime: Long, currentDuration: Long): Long {
                return currentDuration
            }
        }).build()



//    Caffeine.newBuilder().expireAfter(new Expiry<Integer, Employee>() {
//    @Override
//    public long expireAfterCreate(Integer key, Employee emp, long currentTime) {
//        return TimeUnit.SECONDS.toNanos(emp.getExpiryTime());
//    }
//
//    @Override
//    public long expireAfterUpdate(Integer key, Employee emp, long currentTime, long currentDuration) {
//        return currentDuration;
//    }
//
//    @Override
//    public long expireAfterRead(Integer key, Employee emp, long currentTime, long currentDuration) {
//        return currentDuration;
//    }
//}).build();


}