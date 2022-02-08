package ru.dude.tukan_server.entity

import ru.dude.tukan_server.enums.Command


/**
 * @author Vladimir Hrostitisky
 * Date: 06.02.2022
 */
data class Message(val command: Command, val data: Any?)

