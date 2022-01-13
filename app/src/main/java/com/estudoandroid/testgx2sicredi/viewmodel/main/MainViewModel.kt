package com.estudoandroid.testgx2sicredi.viewmodel.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.estudoandroid.testgx2sicredi.models.Checkin
import com.estudoandroid.testgx2sicredi.models.Event
import com.estudoandroid.testgx2sicredi.repositories.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository) : ViewModel() {

    val eventList = MutableLiveData<List<Event>>()
    val event = MutableLiveData<Event>()
    val checkinData = MutableLiveData<Checkin>()
    val errorMessage = MutableLiveData<String>()

    fun getAllEvents(){

        val request = repository.getAllEvents()
        request.enqueue(object  : Callback<List<Event>>{
            override fun onResponse(call: Call<List<Event>>, response: Response<List<Event>>) {
                eventList.postValue(response.body());
            }

            override fun onFailure(call: Call<List<Event>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
    }

    fun getEvent(id: String){
        val request = repository.getEvent(id)
        request.enqueue(object  : Callback<Event>{
            override fun onResponse(call: Call<Event>, response: Response<Event>) {
                event.postValue(response.body())
            }

            override fun onFailure(call: Call<Event>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
    }

    fun checkin(checkin: Checkin){
        val response = repository.checkin(checkin)
        response.enqueue(object : Callback<Checkin>{
            override fun onResponse(call: Call<Checkin>, response: Response<Checkin>) {
                checkinData.postValue(response.body())
            }

            override fun onFailure(call: Call<Checkin>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
    }

}