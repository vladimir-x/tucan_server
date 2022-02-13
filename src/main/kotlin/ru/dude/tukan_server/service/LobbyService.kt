package ru.dude.tukan_server.service

import org.springframework.stereotype.Service
import ru.dude.tukan_server.converter.Converters
import ru.dude.tukan_server.dto.JoinToLobbyDto
import ru.dude.tukan_server.dto.MemberDto
import ru.dude.tukan_server.entity.Lobby
import ru.dude.tukan_server.entity.Member
import ru.dude.tukan_server.enums.Command
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap


/**
 * @author Vladimir Hrostitisky
 * Date: 06.02.2022
 */
@Service
class LobbyService(val sessionService: SessionService) {

    // host -> lobby
    val lobbies: ConcurrentMap<String, Lobby> = ConcurrentHashMap()

    // если будет часто использоваться то ещё один мэп завести
    private fun getLobbyById(lobbyId: String?) = lobbies.values.firstOrNull { it.lobbyId == lobbyId }

    fun createLobby(hostSessionId: String, data: MemberDto?) {
        lobbies.computeIfAbsent(hostSessionId) {
            Lobby(hostSessionId).apply {
                members.add(Member(hostSessionId, data?.name.orEmpty()))
            }
        }

        lobbies[hostSessionId]?.let {
            sessionService.send(hostSessionId, Command.CREATE_LOBBY_SUCCESS, Converters.toLobbyDto(it))
        }
    }

    fun closeLobby(hostSessionId: String) {
        lobbies[hostSessionId]?.members?.forEach {
            sessionService.send(it.sessionId, Command.CLOSE_LOBBY_SUCCESS, null)
        }

        lobbies.remove(hostSessionId)
    }

    fun closeMultiplayer(sessionId: String) {
        closeLobby(sessionId)

        lobbies.values.forEach { lobby ->

            val member = lobby.members.firstOrNull { m -> m.sessionId == sessionId }
            if (member != null){
                lobby.members.remove(member)

                lobby.members.forEach {
                    sessionService.send(it.sessionId, Command.LEAVE_PARTY_MEMBER_SUCCESS, Converters.toLobbyDto(lobby))
                }
            }
        }
    }

    fun joinToLobby(sessionId: String, data: JoinToLobbyDto?) {
        getLobbyById(data?.lobbyId)?.let {
            val member = Member(sessionId, data?.member?.name.orEmpty())
            it.members.add(member)

            val lobbyDto = Converters.toLobbyDto(it)
            sessionService.send(sessionId, Command.JOIN_TO_LOBBY_SUCCESS, lobbyDto)
            sessionService.send(it.hostSessionId, Command.JOIN_PARTY_MEMBER_SUCCESS, lobbyDto)
        }


    }

    fun readyToStart(sessionId: String, memberDto: MemberDto?) {

    }

    fun forceStart(sessionId: String) {

    }


}
