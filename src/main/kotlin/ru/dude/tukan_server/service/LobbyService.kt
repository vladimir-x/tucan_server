package ru.dude.tukan_server.service

import org.springframework.stereotype.Service
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
    private fun getLobbyById(lobbyId: String) = lobbies.values.firstOrNull { it.lobbyId == lobbyId }

    fun createLobby(hostSessionId: String, data: Any?) {
        lobbies.computeIfAbsent(hostSessionId) {
            Lobby(hostSessionId).apply {
                members.add(Member(hostSessionId, ""))
            }
        }

        sessionService.send(hostSessionId, Command.CREATE_LOBBY_SUCCESS, lobbies[hostSessionId]?.lobbyId)
    }

    fun closeLobby(hostSessionId: String) {
        lobbies.remove(hostSessionId)
    }

    fun joinToLobby(sessionId: String, data: Any?) {
        val lobbyId: String = data.toString()
        getLobbyById(lobbyId)?.let {
            val member = Member(sessionId, "")
            it.members.add(member)
            sessionService.send(sessionId, Command.JOIN_TO_LOBBY_SUCCESS, lobbyId)
            sessionService.send(it.hostSessionId, Command.JOIN_PARTY_MEMBER_SUCCESS, lobbyId)
        }


    }

    fun readyToStart(sessionId: String, data: Any?) {

    }

    fun forceStart(sessionId: String, data: Any?) {

    }
}
