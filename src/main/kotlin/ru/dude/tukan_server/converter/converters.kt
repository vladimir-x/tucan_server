package ru.dude.tukan_server.converter

import ru.dude.tukan_server.dto.*
import ru.dude.tukan_server.entity.GameState
import ru.dude.tukan_server.entity.Lobby
import ru.dude.tukan_server.entity.Member


/**
 * @author Vladimir Hrostitisky
 * Date: 11.02.2022
 */
object Converters {

    fun toLobbyDto(lobby: Lobby) = LobbyDto(
        lobby.lobbyId,
        lobby.members.map { toMemberDto(it) },
        toGameStateDto(lobby.gameState.get())
    )

    fun toMemberDto(member: Member) = MemberDto(member.memberId, member.name)

    fun toGameStateDto(state: GameState?) = state?.let {
        GameStateDto(
            state.island.name,
            state.island.roundCount,
            state.gameStart,
            state.gameEnd,
            state.currentRound,
            state.currentTurn
        )
    }
}
