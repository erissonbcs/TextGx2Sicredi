package com.estudoandroid.testgx2sicredi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.estudoandroid.testgx2sicredi.databinding.ActivityDetailBinding
import com.estudoandroid.testgx2sicredi.repositories.MainRepository
import com.estudoandroid.testgx2sicredi.rest.RetrofitService
import com.estudoandroid.testgx2sicredi.utils.DateFormat
import com.estudoandroid.testgx2sicredi.viewmodel.main.MainViewModel
import com.estudoandroid.testgx2sicredi.viewmodel.main.MainViewModelFactory

class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding
    lateinit var viewModel : MainViewModel
    private val retrofitService = RetrofitService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, MainViewModelFactory(MainRepository(retrofitService))).get(
            MainViewModel::class.java
        )

        val id: String? = intent.getStringExtra("id")
        showDetail(id.toString())
    }

    private fun showDetail(id: String){
        viewModel.getEvent(id)

        viewModel.event.observe(this, Observer { event ->
             binding.titleDetail.text = event.title
             binding.dateDetail.text = DateFormat.convertLongToTime(event.date);
             binding.descriptionDetail.text = event.description
             binding.priceDetail.text = "R$ "+event.price.toString()

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(this)
                .applyDefaultRequestOptions(requestOptions)
                .load(event.image)
                .into(binding.imageDetail)
        })

        viewModel.errorMessage.observe(this, Observer {  message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })
    }

}