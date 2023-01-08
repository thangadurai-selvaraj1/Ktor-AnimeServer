package com.anime

import com.anime.models.ApiResponse
import com.anime.models.ErrorResponse
import com.anime.plugins.configureRouting
import com.anime.utils.constants.RouteEndPoints
import com.anime.utils.*
import com.anime.utils.constants.Constants
import com.anime.utils.extensions.calculatePage
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {


    @Test
    fun `access root endpoint, assert correct information`() = testApplication {
        application {
            configureRouting()
        }
        client.get(RouteEndPoints.ROOT).apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello, Welcome to My Api", bodyAsText())
        }
    }

    @Test
    fun `access all heroes endpoint, assert correct information`() = testApplication {
        application {
            configureRouting()
        }

        val list = listOf(page1, page2, page3, page4, page5)
        (1..5).forEach {
            client.get("${RouteEndPoints.ANIME_HEROES}?page=$it").apply {
                assertEquals(HttpStatusCode.OK, status)

                val expected = ApiResponse(
                    success = true,
                    message = "Fetched",
                    nextPage = it.calculatePage()[Constants.NEXT_PAGE_KEY],
                    prePage = it.calculatePage()[Constants.PRE_PAGE_KEY],
                    heroes = list[it - 1]
                )

                val actual = Json.decodeFromString<ApiResponse>(bodyAsText())

                assertEquals(expected, actual)
            }
        }
    }

    @Test
    fun `access all heroes endpoint, assert invalid param information`() = testApplication {
        application {
            configureRouting()
        }

            client.get("${RouteEndPoints.ANIME_HEROES}?page=6").apply {
                assertEquals(HttpStatusCode.BadRequest, status)

                val expected = ApiResponse(
                    success = false,
                    message = "Invalid Param",
                    heroes = emptyList()
                )

                val actual = Json.decodeFromString<ApiResponse>(bodyAsText())

                assertEquals(expected, actual)
            }
    }


    @Test
    fun `access search heroes endpoint, assert correct information`() = testApplication {
        application {
            configureRouting()
        }

        client.get("${RouteEndPoints.SEARCH_HEROES}?name=Boruto").apply {
            assertEquals(HttpStatusCode.OK, status)

            val expected = ApiResponse(
                success = true,
                message = "Fetched",
                heroes = listOf(page2.first())
            )
            val actual = Json.decodeFromString<ApiResponse>(bodyAsText())

            assertEquals(expected, actual)
        }
    }

    @Test
    fun `access search heroes endpoint, assert wrong search name information`() = testApplication {
        application {
            configureRouting()
        }

        client.get("${RouteEndPoints.SEARCH_HEROES}?name=pipoii").apply {
            assertEquals(HttpStatusCode.OK, status)

            val expected =
            ApiResponse(success = true, message = "Fetched")
            val actual = Json.decodeFromString<ApiResponse>(bodyAsText())

            assertEquals(expected, actual)
        }
    }

    @Test
    fun `access invalid endpoint, assert true`() = testApplication {
        application {
            configureRouting()
        }

        client.get("invalid").apply {
            val expected = ErrorResponse(
                message = "Not Found",
                code = 404,
                success = false
            )
            val actual = Json.decodeFromString<ErrorResponse>(bodyAsText())

            assertEquals(expected, actual)
        }
    }


}