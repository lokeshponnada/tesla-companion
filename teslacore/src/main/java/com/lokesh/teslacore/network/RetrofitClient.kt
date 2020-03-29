package com.lokesh.teslacore.network

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class RetrofitClient{

    companion object{


        private val logClient = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

        private val client: OkHttpClient =
            OkHttpClient.Builder().addInterceptor {
                val newRequest: Request = it.request().newBuilder()
                    .addHeader("Authorization", "Bearer YOUR_AUTH_TOKEN")
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

