package ru.dude.tukan_server.service

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import ru.dude.tukan_server.dto.BaseDto
import ru.dude.tukan_server.entity.Message
import ru.dude.tukan_server.enums.Command
import java.util.concurrent.ConcurrentHashMap


/**
 * @author Vladimir Hrostitisky
 * Date: 06.02.2022
 */
@Service
class SessionService(
    val objectMapper: ObjectMapper
) {

    var sessions: MutableMap<String, WebSocketSession> = ConcurrentHashMap()

    fun add(session: WebSocketSession) {
        sessions[session.id] = session
    }

    fun remove(session: WebSocketSession) {
        sessions.remove(session.id)
    }

    fun send(sessionId: String, command: Command, data: BaseDto?) {
        val payload = objectMapper.writeValueAsString(Message(command, data))
        sessions[sessionId]?.sendMessage(TextMessage(payload))
        println("[socket send] $sessionId $payload")
    }

}
