package com.estudoandroid.testgx2sicredi.rest

import com.estudoandroid.testgx2sicredi.models.Event
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

    @GET("events")
    fun getAllEvents(): Call<List<Event>>

    companion object {
        private val retrofitService : RetrofitService by lazy{

            val retrofit = Retrofit.Builder()
                .baseUrl("http://5f5a8f24d44d640016169133.mockapi.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(RetrofitService::class.java)

        }

        fun getInstance() : RetrofitService {
            return  retrofitService
        }
    }
}