package com.example.retrofitapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitapplication.databinding.NewsItemLayoutBinding
import com.example.retrofitapplication.model.NewsModel

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    private val news = mutableListOf<NewsModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            NewsItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = news.size

    fun setNews(news:List<NewsModel>){
        this.news.addAll(news)
        notifyDataSetChanged()
    }

    fun clearNews(){
        news.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: NewsItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        private lateinit var model:NewsModel
        fun bind(){
            model = news[adapterPosition]
            Glide.with(App.instance.getContext()).load(model.cover).into(binding.newsCoverImageView)
            binding.titleTextView.text = model.titleKA
        }
    }
}