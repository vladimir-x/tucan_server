package ru.dude.tukan_server.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


/**
 * @author Vladimir Hrostitisky
 * Date: 02.02.2022
 */
@RestController
class InfoController(
    val socketHandler: SocketHandler
) {

    @RequestMapping("/info")
    fun health(): String {
        return "sessions size: " + socketHandler.sessions.size
    }
}
