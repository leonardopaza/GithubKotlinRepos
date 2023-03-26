package com.lp.data_remote.utils

import com.google.gson.GsonBuilder
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object WebServiceFactory {
    inline fun <reified T> createWebService(
        okHttpClient: OkHttpClient,
        url: String = "",
        acceptLenient: Boolean = false
    ): T {
        val gsonFactory = if (acceptLenient) {
            val gson = GsonBuilder().setLenient().create()
            GsonConverterFactory.create(gson)
        } else {
            GsonConverterFactory.create()
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(gsonFactory)
            .build()
        return retrofit.create()
    }

    fun provideOkHttpClient(isDebugVersion: Boolean) : OkHttpClient =
        OkHttpClient.Builder()
            .dispatcher(dispatcher())
            .httpLoggingInterceptor(isDebugVersion)
            .connectTimeout(TIMEOUT_DURATION_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_DURATION_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_DURATION_SECONDS, TimeUnit.SECONDS)
            .build()

    private fun OkHttpClient.Builder.httpLoggingInterceptor(isDebugVersion: Boolean) =
        when (isDebugVersion) {
            true -> {
                val interceptor = HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
                addInterceptor(interceptor)
            }
            else -> this
        }

    private fun dispatcher() = Dispatcher().run {
        maxRequests = 1
        maxRequestsPerHost = 1
        this
    }
}