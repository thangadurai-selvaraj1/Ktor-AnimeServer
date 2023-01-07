package com.anime.models

@kotlinx.serialization.Serializable
data class ErrorResponse(
    val success:Boolean,
    val message:String,
    val code :Int
)
