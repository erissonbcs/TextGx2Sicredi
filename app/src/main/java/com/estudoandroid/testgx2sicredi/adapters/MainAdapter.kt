package com.estudoandroid.testgx2sicredi.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.estudoandroid.testgx2sicredi.R
import com.estudoandroid.testgx2sicredi.databinding.ResItemEventBinding
import com.estudoandroid.testgx2sicredi.models.Event
import com.estudoandroid.testgx2sicredi.utils.DateFormat

class MainAdapter(private val onItemClicked: (Event) -> Unit) : RecyclerView.Adapter<MainViewHolder>() {

    private var events = mutableListOf<Event>()

    fun setEventList(events: List<Event>){

        this.events = events.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ResItemEventBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val event = events[position]
        holder.bind(event, onItemClicked)
    }

    override fun getItemCount(): Int {
        return events.size
    }

}

class MainViewHolder(val binding: ResItemEventBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(event: Event, onItemClicked: (Event) -> Unit) {

        binding.title.text = event.title
        binding.date.text = DateFormat.convertLongToTime(event.date);

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(itemView.context)
            .applyDefaultRequestOptions(requestOptions)
            .load(event.image)
            .into(binding.image)

         itemView.setOnClickListener {
            onItemClicked(event)
         }
    }

}