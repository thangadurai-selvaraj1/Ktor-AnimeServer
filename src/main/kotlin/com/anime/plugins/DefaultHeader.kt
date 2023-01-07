package com.anime.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.defaultheaders.*
import java.time.Duration

fun Application.configureDefaultHeader() {
    install(DefaultHeaders){
        val seconds = Duration.ofDays(365).seconds
        header(name = HttpHeaders.CacheControl, value = "public, max-age=$seconds, immutable")
    }
}