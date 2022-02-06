package ru.dude.tukan_server.controller

import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.util.concurrent.CopyOnWriteArrayList


/**
 * @author Vladimir Hrostitisky
 * Date: 04.02.2022
 */
@Component
class SocketHandler : TextWebSocketHandler() {

    var sessions = CopyOnWriteArrayList<Any>()

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        session.sendMessage(TextMessage("HELLO FROM TUCANIA ///" + message.payload))
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        sessions.add(session)
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        sessions.remove(session)
    }

}
