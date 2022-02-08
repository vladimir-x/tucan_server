package ru.dude.tukan_server.entity

import java.util.concurrent.CopyOnWriteArraySet
import java.util.concurrent.atomic.AtomicLong
import java.util.concurrent.atomic.AtomicReference


/**
 * @author Vladimir Hrostitisky
 * Date: 08.02.2022
 */
class Lobby(

    val hostSessionId: String,

    val members: MutableSet<Member> = CopyOnWriteArraySet(),

    val lobbyId: String = generatorId.getAndIncrement().toString(),

    val gameState: AtomicReference<GameState> = AtomicReference<GameState>(),

    ) {
    companion object {
        val generatorId = AtomicLong()
    }
}
