package com.estudoandroid.testgx2sicredi

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.estudoandroid.testgx2sicredi.databinding.ActivityDetailBinding
import com.estudoandroid.testgx2sicredi.models.Checkin
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

        binding.btCheckin.setOnClickListener(View.OnClickListener {
            checkin(id.toString())
        })


        showDetail(id.toString())
    }

    private fun showDetail(id: String){
        viewModel.getEvent(id)

        viewModel.event.observe(this, Observer { event ->
             binding.titleDetail.text = event.title
             binding.dateDetail.text = "Data "+DateFormat.convertLongToTime(event.date);
             binding.descriptionDetail.text = event.description
             binding.priceDetail.text = "Valor R$ "+event.price.toString()

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            var image = event.image
            if (Build.VERSION.SDK_INT > 27 && !event.image.contains("https")) {
                image = image.replace("http", "https")
            }

            Glide.with(this)
                .applyDefaultRequestOptions(requestOptions)
                .load(image)
                .into(binding.imageDetail)

            var imageMap = "https://maps.googleapis.com/maps/api/staticmap?center="+event.latitude.toString()+
                    ","+event.longitude.toString()+"&zoom=12&size=400x400" +
                    "&markers=red:blue%7Clabel:S%7C"+event.latitude.toString()+","+event.longitude.toString()+
                    "&key="+getString(R.string.google_maps_key)

            Glide.with(this)
                .applyDefaultRequestOptions(requestOptions)
                .load(imageMap)
                .into(binding.imageMap)
        })

        viewModel.errorMessage.observe(this, Observer {  message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })
    }

    private fun checkin(id: String){
        val checkin = Checkin(id, "Erisson Batista", "erissonbcs@gmail.com")

        viewModel.checkin(checkin)

        viewModel.checkinData.observe(this, Observer { response ->
            finish()
            startActivity(Intent(this,SucessActivity::class.java))
        })

        viewModel.errorMessage.observe(this, Observer {  message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })
    }

}