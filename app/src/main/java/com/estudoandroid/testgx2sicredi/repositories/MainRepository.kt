package com.estudoandroid.testgx2sicredi.repositories

import com.estudoandroid.testgx2sicredi.rest.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllEvents() = retrofitService.getAllEvents()

    fun getEvent(id: String) = retrofitService.getEvent(id)
}