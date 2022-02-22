package ru.dude.tukan_server.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


/**
 * @author Vladimir Hrostitisky
 * Date: 22.02.2022
 */
@Configuration
class ObjectMapperConfig {

    @Bean
    fun objectMapper() = ObjectMapper().registerModule(KotlinModule())

}
