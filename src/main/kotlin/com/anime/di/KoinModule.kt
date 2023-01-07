package com.anime.di

import com.anime.repo.HeroRepository
import com.anime.repo.HeroRepositoryImpl
import org.koin.dsl.module

val koinModule = module {
    single<HeroRepository> {
        HeroRepositoryImpl()
    }
}