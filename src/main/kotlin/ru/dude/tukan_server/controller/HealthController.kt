package ru.dude.tukan_server.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


/**
 * @author Vladimir Hrostitisky
 * Date: 02.02.2022
 */
@RestController
class HealthController {

    @RequestMapping("/health")
    fun health() = "OK. I'm alive 57"
}
