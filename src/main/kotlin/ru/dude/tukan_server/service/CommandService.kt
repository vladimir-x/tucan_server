package ru.dude.tukan_server.service

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service
import ru.dude.tukan_server.dto.BaseDto
import ru.dude.tukan_server.enums.Command


/**
 * @author Vladimir Hrostitisky
 * Date: 06.02.2022
 */
@Service
class CommandService(
    val lobbyService: LobbyService,
    val objectMapper: ObjectMapper,
) {


    private inline fun <reified DTO : BaseDto> readValue(json: JsonNode): DTO {
        val jsonData = json.findValue("data")
        val data = objectMapper.treeToValue(jsonData, DTO::class.java)
        return data
    }

    fun execute(sessionId: String, messageString: String) {
        val json = objectMapper.readTree(messageString)
        val command = enumValueOf<Command>(json.findValuesAsText("command").first())

        when (command) {
            Command.CREATE_LOBBY -> lobbyService.createLobby(sessionId, readValue(json))
            Command.CLOSE_LOBBY -> lobbyService.closeLobby(sessionId)
            Command.CLOSE_MULTIPLAYER -> lobbyService.closeMultiplayer(sessionId)
            Command.JOIN_TO_LOBBY -> lobbyService.joinToLobby(sessionId, readValue(json))
            Command.READY_TO_START -> lobbyService.readyToStart(sessionId, readValue(json))
            Command.FORCE_START -> lobbyService.forceStart(sessionId)
        }

    }


}
