package ru.dude.tukan_server.converter

import ru.dude.tukan_server.dto.LobbyDto
import ru.dude.tukan_server.dto.MemberDto
import ru.dude.tukan_server.entity.Lobby
import ru.dude.tukan_server.entity.Member


/**
 * @author Vladimir Hrostitisky
 * Date: 11.02.2022
 */
object Converters {

    public fun toLobbyDto(lobby: Lobby) = LobbyDto(lobby.lobbyId, lobby.members.map { toMemberDto(it) })

    public fun toMemberDto(member: Member) = MemberDto(member.memberId, member.name)
}
