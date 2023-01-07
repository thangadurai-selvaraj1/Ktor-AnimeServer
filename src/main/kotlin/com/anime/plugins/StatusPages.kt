package com.anime.plugins

import com.anime.models.ErrorResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureStatusPages() {
    install(StatusPages) {
        status(HttpStatusCode.NotFound) { call, statusCode ->
            call.respond(
                message = ErrorResponse(
                    message = "Not Found",
                    code = statusCode.value,
                    success = false
                )
            )
        }
        status(HttpStatusCode.InternalServerError) { call, statusCode ->
            call.respond(
                message = ErrorResponse(
                    message = "Server Error",
                    code = statusCode.value,
                    success = false
                )
            )
        }
    }
}