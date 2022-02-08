package ru.dude.tukan_server.service

import org.springframework.stereotype.Service
import ru.dude.tukan_server.entity.Message
import ru.dude.tukan_server.enums.Command


/**
 * @author Vladimir Hrostitisky
 * Date: 06.02.2022
 */
@Service
class CommandService(val lobbyService: LobbyService) {

    fun execute(sessionId: String, command: Message?) {
        when (command?.command) {
            Command.CREATE_LOBBY -> lobbyService.createLobby(sessionId, command.data)
            Command.CLOSE_LOBBY -> lobbyService.closeLobby(sessionId)
            Command.JOIN_TO_LOBBY -> lobbyService.joinToLobby(sessionId, command.data)
            Command.READY_TO_START -> lobbyService.readyToStart(sessionId, command.data)
            Command.FORCE_START -> lobbyService.forceStart(sessionId, command.data)
        }
    }


}
