package com.eventersapp.gojek_trending.dagger


import com.eventersapp.gojek_trending.BuildConfig
import com.eventersapp.gojek_trending.GoJekApp
import com.eventersapp.gojek_trending.dagger.scope.AppScope
import com.eventersapp.gojek_trending.util.InternetChecker
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.type.TypeFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import dagger.Lazy
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

private const val CONNECTION_TIME = 90L
private const val CACHE_SIZE = (10 * 1024 * 1024).toLong()
private const val CACHE_NAME = "JoGekofflineCache"

@Module
class NetWorkModule {

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

    @Provides
    fun provideCacheInterceptor(): CacheInterceptor =
        CacheInterceptor(offlineValidCache())

    @Provides
    fun provideOfflineCacheInterceptor(internetChecker: InternetChecker): OfflineCacheInterceptor =
        OfflineCacheInterceptor(
            offlineValidCache(),
            internetChecker
        )

    @Provides
    fun provideFile(applicationContext: GoJekApp): File =
        File(applicationContext.cacheDir, CACHE_NAME)

    @Provides
    fun provideCache(file: File): Cache =
        Cache(file, CACHE_SIZE)

    @Provides
    @AppScope
    fun provideObjectMapper() = jacksonObjectMapper().apply {
        setSerializationInclusion(JsonInclude.Include.NON_NULL)
        disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
        disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        registerModule(KotlinModule())
    }

    @Provides
    @AppScope
    fun provideJacksonConverterFactory(objectMapper: ObjectMapper): JacksonConverterFactory =
        JacksonConverterFactory.create(objectMapper)

    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        offlineCacheInterceptor: OfflineCacheInterceptor,
        cacheInterceptor: CacheInterceptor,
        cache: Cache
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(CONNECTION_TIME, TimeUnit.SECONDS)
            readTimeout(CONNECTION_TIME, TimeUnit.SECONDS)
            writeTimeout(CONNECTION_TIME, TimeUnit.SECONDS)
            addInterceptor(httpLoggingInterceptor)
            addInterceptor(offlineCacheInterceptor)
            addNetworkInterceptor(cacheInterceptor)
            followRedirects(true)
            followSslRedirects(true)
            cache(cache)
            retryOnConnectionFailure(false)
        }.build()
    }

    @Provides
    @AppScope
    fun createRetrofit(
        okHttpClient: Lazy<OkHttpClient>,
        converterFactory: JacksonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl())
            .client(okHttpClient.get())
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    @AppScope
    fun typeFactory(
        objectMapper: ObjectMapper
    ): TypeFactory {
        return objectMapper.typeFactory
    }


    private fun baseUrl() = BuildConfig.HOST
    private fun offlineValidCache() = 2
}
