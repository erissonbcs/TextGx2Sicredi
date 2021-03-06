package com.estudoandroid.testgx2sicredi

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
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
        openEvent(it.id)
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

    private fun openEvent(id: String){

        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("id", id)

        if (Build.VERSION.SDK_INT >= 21) {
            val activityOptions = ActivityOptions.makeSceneTransitionAnimation(this)
            startActivity(intent, activityOptions.toBundle())
        }else{
            startActivity(intent)
        }

    }
}