package ru.dude.tukan_server.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry
import ru.dude.tukan_server.controller.SocketHandler


/**
 * @author Vladimir Hrostitisky
 * Date: 02.02.2022
 */
@Configuration
@EnableWebSocket
class WebSocketConfig(
    val socketHandler: SocketHandler
): WebSocketConfigurer {

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(socketHandler, "/exchange")
            //.setAllowedOrigins("*")
            .setAllowedOrigins("https://tukanchick.herokuapp.com","http://localhost:8080/","http://192.168.1.68:8080/")
    }

}
