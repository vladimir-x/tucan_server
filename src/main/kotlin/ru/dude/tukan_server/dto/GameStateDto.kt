package ru.dude.tukan_server.dto

/**
 * @author Vladimir Hrostitisky
 * Date: 11.02.2022
 */
data class GameStateDto(
    val island: String,

    val roundCount: Long,

    val gameStart: Boolean = false,

    val gameEnd: Boolean = false,

    val currentRound: Long,

    val currentTurn: Long,

    ) : BaseDto
