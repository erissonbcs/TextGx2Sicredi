package com.estudoandroid.testgx2sicredi.rest

import android.os.Build
import com.estudoandroid.testgx2sicredi.models.Checkin
import com.estudoandroid.testgx2sicredi.models.Event
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RetrofitService {

    @GET("api/events")
    fun getAllEvents(): Call<List<Event>>

    @GET("api/events/{id}")
    fun getEvent(@Path("id") id: String): Call<Event>

    @POST("api/checkin")
    fun checkin(@Body checkin: Checkin): Call<Checkin>

    companion object {
        private val retrofitService : RetrofitService by lazy{

            var url = "https://5f5a8f24d44d640016169133.mockapi.io/"
            if (Build.VERSION.SDK_INT < 28) {
                url = "http://5f5a8f24d44d640016169133.mockapi.io/"
            }

            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(RetrofitService::class.java)

        }

        fun getInstance() : RetrofitService {
            return  retrofitService
        }
    }
}