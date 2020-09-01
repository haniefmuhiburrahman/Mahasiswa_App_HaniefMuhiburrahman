package com.hanief.mahasiswa_app_haniefmuhiburrahman.config

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object NetworkModule {

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("http://192.168.43.213/mentoring_kotlin_week4/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun service() : ApiService = getRetrofit().create(ApiService::class.java)

}