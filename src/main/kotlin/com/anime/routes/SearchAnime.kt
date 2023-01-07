package com.anime.routes


import com.anime.models.ApiResponse
import com.anime.repo.HeroRepository
import com.anime.utils.constants.RouteEndPoints
import com.anime.utils.constants.RouteParams
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.searchAnimeCharacter() {
    val heroRepository by inject<HeroRepository>()

    get(RouteEndPoints.SEARCH_HEROES) {
        runCatching {
            val name = call.request.queryParameters[RouteParams.NAME]
            name
        }.onSuccess {
           val heroes =  heroRepository.searchHeroes(name =  it)
            call.respond(
                message = heroes,
                status = HttpStatusCode.OK
            )
        }.onFailure {
            if (it is Exception)
                call.respond(
                    message = ApiResponse(success = false, message = "Check Invalid Param"),
                    status = HttpStatusCode.BadRequest
                )
        }
    }
}