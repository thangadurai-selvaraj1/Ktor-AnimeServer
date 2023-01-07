package com.anime.routes

import com.anime.utils.constants.RouteEndPoints
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.root(){
    get(RouteEndPoints.ROOT){
        call.respond(
            message = "Hello, Welcome to My Api",
            status = HttpStatusCode.OK
        )
    }
}