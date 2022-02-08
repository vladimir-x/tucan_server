package ru.dude.tukan_server.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.dude.tukan_server.service.LobbyService
import ru.dude.tukan_server.service.SessionService


/**
 * @author Vladimir Hrostitisky
 * Date: 02.02.2022
 */
@RestController
class InfoController(
    val sessionService: SessionService,
    val lobbyService: LobbyService,
) {

    @RequestMapping("/info")
    fun health(): String =
        buildString {
            append("sessions size: ${sessionService.sessions.size}\n")
            append("lobbies size: ${lobbyService.lobbies.size}\n")
        }
}
