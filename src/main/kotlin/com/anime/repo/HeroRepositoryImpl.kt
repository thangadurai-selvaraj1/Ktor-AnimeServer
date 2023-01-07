package com.anime.repo

import com.anime.models.ApiResponse
import com.anime.utils.constants.Constants
import com.anime.utils.extensions.calculatePage
import com.anime.utils.extensions.searchAnime
import com.anime.utils.heroes

class HeroRepositoryImpl : HeroRepository {
    override suspend fun getAllHeroes(page: Int) =
        ApiResponse(
            success = true,
            message = "Fetched",
            prePage = page.calculatePage()[Constants.PRE_PAGE_KEY],
            nextPage = page.calculatePage()[Constants.NEXT_PAGE_KEY],
            heroes = heroes[page] ?: emptyList()
        )


    override suspend fun searchHeroes(name: String?): ApiResponse {
       return  ApiResponse(
           success = true,
           message = "Fetched",
           heroes = name.searchAnime(),
       )
    }
}