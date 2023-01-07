package com.anime.plugins

import com.anime.routes.getAllAnime
import com.anime.routes.root
import com.anime.routes.searchAnimeCharacter
import com.anime.utils.constants.Constants
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*

fun Application.configureRouting() {
    routing {
        root()
        getAllAnime()
        searchAnimeCharacter()

        static ("/${Constants.IMAGES_KEY}"){
            resources(Constants.IMAGES_KEY)
        }
    }
}
