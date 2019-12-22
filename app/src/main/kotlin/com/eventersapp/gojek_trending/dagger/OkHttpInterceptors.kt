package com.eventersapp.gojek_trending.dagger

import com.eventersapp.gojek_trending.util.InternetChecker
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

const val HEADER_CACHE_CONTROL = "Cache-Control"
const val HEADER_PRAGMA = "Pragma"

class CacheInterceptor(private val maxCacheHours: Int) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val cacheControl = CacheControl.Builder()
            .maxAge(maxCacheHours, TimeUnit.HOURS)
            .build();

        val response = chain.proceed(chain.request())
        return response.newBuilder()
            .header(HEADER_CACHE_CONTROL, cacheControl.toString())
            .removeHeader(HEADER_PRAGMA)
            .build()
    }
}


class OfflineCacheInterceptor(private val maxCacheHours: Int, private val internetChecker: InternetChecker) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!internetChecker.isInternetAvailable()) {

            val cacheControl = CacheControl.Builder()
                .onlyIfCached()
                .maxStale(maxCacheHours, TimeUnit.HOURS)
                .build();

            val offlineRequest = chain.request().newBuilder()
                .cacheControl(cacheControl)
                .removeHeader(HEADER_PRAGMA)
                .build();
            return chain.proceed(offlineRequest);
        }
        return chain.proceed(chain.request())
    }
}