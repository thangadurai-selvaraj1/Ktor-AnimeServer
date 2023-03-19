package com.anime.routes

import com.anime.models.ApiResponse
import com.anime.repo.HeroRepository
import com.anime.utils.constants.RouteEndPoints
import com.anime.utils.constants.RouteParams
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.delay
import org.koin.ktor.ext.inject

fun Route.getAllAnime() {
    val heroRepository by inject<HeroRepository>()

    get(RouteEndPoints.ANIME_HEROES) {
        runCatching {
            val page = call.request.queryParameters[RouteParams.PAGE]?.toInt() ?: 1
            require(page in 1..5)
            page
        }.onSuccess {
            call.respond(
                message = heroRepository.getAllHeroes(page = it),
                status = HttpStatusCode.OK
            )
        }.onFailure {
            if (it is IllegalArgumentException)
                call.respond(
                    message = ApiResponse(success = false, message = "Invalid Param"),
                    status = HttpStatusCode.BadRequest
                )

            if (it is NumberFormatException)
                call.respond(
                    message = ApiResponse(success = false, message = "Only numbers are allowed"),
                    status = HttpStatusCode.BadRequest
                )

        }
    }
}