package com.example.retrofitapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitapplication.databinding.NewsItemLayoutBinding
import com.example.retrofitapplication.databinding.ProgressLayoutBinding
import com.example.retrofitapplication.model.NewsModel

class RecyclerAdapter : PagingDataAdapter<NewsModel, RecyclerView.ViewHolder>(REPO_COMPARATOR) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NewsViewHolder -> holder.bind()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var binding =
            NewsItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return NewsViewHolder(binding)
    }

    inner class NewsViewHolder(private val binding: NewsItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var model: NewsModel
        fun bind() {
            model = getItem(absoluteAdapterPosition)!!
            Glide.with(binding.root.context).load(model.cover).into(binding.newsCoverImageView)
            binding.titleTextView.text = model.titleKA
        }
    }


    companion object {

        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<NewsModel>() {
            override fun areItemsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean =
                oldItem.id == newItem.id
        }
    }
}