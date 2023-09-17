package dev.volyanyk.riddle.config

import dev.volyanyk.riddle.handler.MessageHandler
import org.springframework.web.servlet.function.router



fun appRouter(messageHandler: MessageHandler) = router {
    "/api".nest {
        "/users".nest {
            POST(messageHandler::createMessage)
            GET("/{id}", messageHandler::getById)
        }
    }
}