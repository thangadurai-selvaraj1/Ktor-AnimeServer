package com.anime.repo

import com.anime.models.ApiResponse

interface HeroRepository {
    suspend fun getAllHeroes(page: Int = 1): ApiResponse

    suspend fun searchHeroes(name: String?): ApiResponse
}