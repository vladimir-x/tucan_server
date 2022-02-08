package ru.dude.tukan_server.enums


/**
 * @author Vladimir Hrostitisky
 * Date: 06.02.2022
 */
enum class Command {

    //receive
    CREATE_LOBBY,
    CLOSE_LOBBY,
    JOIN_TO_LOBBY,
    READY_TO_START,
    FORCE_START,


    //send
    CREATE_LOBBY_SUCCESS,
    JOIN_TO_LOBBY_SUCCESS,
    JOIN_PARTY_MEMBER_SUCCESS,


}
