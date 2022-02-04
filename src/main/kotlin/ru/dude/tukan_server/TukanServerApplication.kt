package ru.dude.tukan_server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class TukanServerApplication

fun main(args: Array<String>) {
    runApplication<TukanServerApplication>(*args)
}
