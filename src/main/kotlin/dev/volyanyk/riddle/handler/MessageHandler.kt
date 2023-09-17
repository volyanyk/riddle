package dev.volyanyk.riddle.handler

import dev.volyanyk.riddle.dto.MessageDto
import dev.volyanyk.riddle.model.Message
import org.springframework.web.servlet.function.ServerRequest
import org.springframework.web.servlet.function.ServerResponse
import java.util.UUID
import dev.volyanyk.riddle.model.Error
import dev.volyanyk.riddle.service.MessageService

class MessageHandler(private val messageService: MessageService) {
    fun createMessage(request:ServerRequest) : ServerResponse{
        val messageRequest = request.body(MessageDto::class.java)

        val createdMessageResponse = messageService.create(message = messageRequest.toModel())

        return ServerResponse.ok().body(createdMessageResponse)
    }
    fun getById(request:ServerRequest) : ServerResponse{
        val id = request.pathVariable("id")?: badRequestResponse("incorrect id value")
        val uuid = UUID.fromString(id.toString());

        val messageResponse = messageService.getMessage(uuid)?.toDto()

        return  messageResponse?.let { response ->
            ServerResponse.ok()
                .body(response)
        }
            ?: notFoundResponse(uuid)
    }

    private fun badRequestResponse(reason: String): ServerResponse =
        ServerResponse.badRequest()
            .body(
                Error(reason)
            )

    private fun notFoundResponse(id: UUID): ServerResponse =
        ServerResponse.badRequest()
            .body(
                Error("Message with id: $id was not found.")
            )


    private fun MessageDto.toModel(): Message =
        Message(
            ttl = this.ttl,
            accessCode = this.accessCode,
            message = this.message,
        )

    private fun Message.toDto(): MessageDto =
        MessageDto(
            ttl = this.ttl,
            accessCode = this.accessCode,
            message = this.message,
        )

}