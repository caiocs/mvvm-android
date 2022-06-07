package com.caiosilva.mvvm.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.caiosilva.mvvm.R
import com.caiosilva.mvvm.databinding.ResItemLiveBinding
import com.caiosilva.mvvm.models.Live

class MainAdapter(private val onItemClicked: (Live) -> Unit) : RecyclerView.Adapter<MainViewHolder>() {

    private var lives = mutableListOf<Live>()

    fun setLiveList(list: List<Live>) {
        this.lives = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ResItemLiveBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val live = lives[position]
        holder.bind(live, onItemClicked)
    }

    override fun getItemCount(): Int {
        return lives.size
    }

}

class MainViewHolder(val binding: ResItemLiveBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(live: Live, onItemClicked: (Live) -> Unit) {
        Log.v("CaioSilva", "on Bind")
        binding.title.text = live.title
        binding.autor.text = live.author

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(itemView.context)
            .applyDefaultRequestOptions(requestOptions)
            .load(live.thumbnailUrl)
            .into(binding.image)

        itemView.setOnClickListener {
            onItemClicked(live)
        }
    }

}