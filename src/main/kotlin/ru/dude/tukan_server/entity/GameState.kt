package ru.dude.tukan_server.entity

import ru.dude.tukan_server.rules.Island


/**
 * @author Vladimir Hrostitisky
 * Date: 08.02.2022
 */
class GameState(

    var island: Island,

    var gameStart: Boolean = false,

    var gameEnd: Boolean = false,

    val currentRound: Long,

    val currentTurn: Long,
)
