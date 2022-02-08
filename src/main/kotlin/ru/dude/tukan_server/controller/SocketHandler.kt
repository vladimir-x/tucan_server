package ru.dude.tukan_server.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import ru.dude.tukan_server.entity.Message
import ru.dude.tukan_server.service.CommandService
import ru.dude.tukan_server.service.LobbyService
import ru.dude.tukan_server.service.SessionService


/**
 * @author Vladimir Hrostitisky
 * Date: 04.02.2022
 */
@Component
class SocketHandler(
    val commandService: CommandService,
    val sessionService: SessionService,
    val lobbyService: LobbyService,
    val objectMapper: ObjectMapper
) : TextWebSocketHandler() {


    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val command = objectMapper.readValue(message.payload, Message::class.java)
        println("[socket income] $command")
        commandService.execute(session.id, command)
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        println("[socket open] ${session.id}")
        sessionService.add(session)
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        println("[socket close] ${session.id}")
        sessionService.remove(session)
        lobbyService.closeLobby(session.id)
    }


}
