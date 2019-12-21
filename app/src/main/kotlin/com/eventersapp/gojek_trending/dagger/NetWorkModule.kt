package com.eventersapp.gojek_trending.dagger

import com.eventersapp.gojek_trending.BuildConfig
import com.eventersapp.gojek_trending.dagger.scope.AppScope
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
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

private const val CONNECTION_TIME = 90L

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
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(CONNECTION_TIME, TimeUnit.SECONDS)
            readTimeout(CONNECTION_TIME, TimeUnit.SECONDS)
            writeTimeout(CONNECTION_TIME, TimeUnit.SECONDS)
            addInterceptor(httpLoggingInterceptor)
            followRedirects(true)
            followSslRedirects(true)
            cache(null)
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
}
