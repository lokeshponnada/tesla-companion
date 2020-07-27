package com.lokesh.teslacore.network

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class RetrofitClient{

    companion object{

        private val client: OkHttpClient =
            OkHttpClient.Builder().addInterceptor {
                val newRequest: Request = it.request().newBuilder()
                    .addHeader("Authorization", "Bearer AUTH_TOKEN_HERE")
                    .build()
                 it.proceed(newRequest)
            }.build()



        val webservice: Webservice by lazy {
            Retrofit.Builder()
                .client(client)
                .baseUrl("https://owner-api.teslamotors.com/api/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build().create(Webservice::class.java)
        }

    }




}

