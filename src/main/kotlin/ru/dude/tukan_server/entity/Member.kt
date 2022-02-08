package ru.dude.tukan_server.entity

import java.util.concurrent.atomic.AtomicLong


/**
 * @author Vladimir Hrostitisky
 * Date: 06.02.2022
 */
data class Member(
    val sessionId: String,
    val name: String,
    val memberId: String = generatorId.getAndIncrement().toString()
) {
    companion object {
        val generatorId = AtomicLong()
    }
}

