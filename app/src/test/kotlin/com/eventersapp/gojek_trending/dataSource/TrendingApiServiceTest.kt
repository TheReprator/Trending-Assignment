package com.eventersapp.gojek_trending.dataSource

import com.eventersapp.gojek_trending.util.bodyOrThrow
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.net.HttpURLConnection

class TrendingApiServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var trendingApiService: TrendingApiService

    //private val trendingMapper = mockk<TrendingMapper>(relaxed = true)

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val objectMapper = jacksonObjectMapper().apply {
            setSerializationInclusion(JsonInclude.Include.NON_NULL)
            disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
            disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            registerModule(KotlinModule())
        }

        val jacksonConverterFactory = JacksonConverterFactory.create(objectMapper)
        trendingApiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(jacksonConverterFactory)
            .build()
            .create(TrendingApiService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    private fun mockHttpResponse(mockServer: MockWebServer, fileName: String, responseCode: Int) =
        mockServer.enqueue(
            MockResponse()
                .setResponseCode(responseCode)
                .setBody(getJson(fileName))
        )

    private fun getJson(path: String): String {
       return this.javaClass.classLoader!!.getResource(path).readText()
    }

    @Test
    fun `Get Top Trending Repositories`() {
        mockHttpResponse(mockWebServer, "trending_repos.json", HttpURLConnection.HTTP_OK)
        runBlocking {
            val users = trendingApiService.fetchTrendingRepo().body()
            Assert.assertEquals(7, users!!.size)
            Assert.assertEquals("monica", users.first().name)
        }
    }

    @Test(expected = HttpException::class)
    fun `Get Top Trending Repositories and fail`() {
        mockHttpResponse(mockWebServer,"trending_repos.json", HttpURLConnection.HTTP_FORBIDDEN)
        runBlocking {
            trendingApiService.fetchTrendingRepo().bodyOrThrow()
        }
    }
}