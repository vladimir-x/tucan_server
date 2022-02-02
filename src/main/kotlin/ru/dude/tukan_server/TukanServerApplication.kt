package ru.dude.tukan_server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TukanServerApplication

fun main(args: Array<String>) {
    runApplication<TukanServerApplication>(*args)
}
