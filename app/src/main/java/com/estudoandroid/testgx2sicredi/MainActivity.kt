package com.estudoandroid.testgx2sicredi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.estudoandroid.testgx2sicredi.adapters.MainAdapter
import com.estudoandroid.testgx2sicredi.databinding.ActivityMainBinding
import com.estudoandroid.testgx2sicredi.repositories.MainRepository
import com.estudoandroid.testgx2sicredi.rest.RetrofitService
import com.estudoandroid.testgx2sicredi.viewmodel.main.MainViewModel
import com.estudoandroid.testgx2sicredi.viewmodel.main.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    lateinit var viewModel : MainViewModel

    private val retrofitService = RetrofitService.getInstance()

    private val adapter = MainAdapter{
        openDetailEvent(it.id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, MainViewModelFactory(MainRepository(retrofitService))).get(
            MainViewModel::class.java
        )

        binding.recyclerview.adapter = adapter
    }

    override fun onStart() {
        super.onStart()

        viewModel.eventList.observe(this, Observer { events ->
            Log.i("Erisson", "OnStart")
            adapter.setEventList(events)
        })

        viewModel.errorMessage.observe(this, Observer {  message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onResume() {
        super.onResume()

        viewModel.getAllEvents()
    }

    private fun openDetailEvent(id: String){

    }
}