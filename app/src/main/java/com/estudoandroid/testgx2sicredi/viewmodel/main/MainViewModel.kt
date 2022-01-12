package com.estudoandroid.testgx2sicredi.viewmodel.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.estudoandroid.testgx2sicredi.models.Event
import com.estudoandroid.testgx2sicredi.repositories.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository) : ViewModel() {

    val eventList = MutableLiveData<List<Event>>()
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

}