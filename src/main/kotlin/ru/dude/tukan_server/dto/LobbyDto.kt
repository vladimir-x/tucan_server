package ru.dude.tukan_server.dto


/**
 * @author Vladimir Hrostitisky
 * Date: 11.02.2022
 */
data class LobbyDto(val lobbyId: String, val members: Iterable<MemberDto>) : BaseDto
