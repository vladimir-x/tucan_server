package ru.dude.tukan_server.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.*
import ru.dude.tukan_server.controller.SocketHandler


/**
 * @author Vladimir Hrostitisky
 * Date: 02.02.2022
 */
@Configuration
@EnableWebSocket
class WebSocketConfig: WebSocketConfigurer {

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(SocketHandler(), "/name")
            .setAllowedOrigins("https://tukanchick.herokuapp.com","http://localhost:8080/")
    }


}
