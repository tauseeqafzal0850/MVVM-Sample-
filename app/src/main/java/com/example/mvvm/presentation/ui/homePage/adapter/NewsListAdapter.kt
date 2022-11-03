package com.example.mvvm.presentation.ui.homePage.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.mvvm.R
import com.example.mvvm.data.responses.NewsResponseDto
import com.example.mvvm.databinding.NewsItemContentBinding
import com.example.mvvm.presentation.utils.Utility
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class NewsListAdapter(private val dataList: ArrayList<NewsResponseDto.Article>) :
    RecyclerView.Adapter<NewsListAdapter.NavigationItemViewHolder>() {

    private lateinit var context: Context

    class NavigationItemViewHolder(private val binding: NewsItemContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: NewsResponseDto.Article) {
            binding.tvNewsTitle.text=news.title
            binding.tvAuthor.text=news.author
            binding.img.load(news.urlToImage) {
                crossfade(true)
                    .placeholder(R.drawable.loading)
                transformations(CircleCropTransformation())
            }
            binding.tvPublishedAt.text=Utility.parseDate(news.publishedAt.toString())

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationItemViewHolder {
        context = parent.context
        val binding =
            NewsItemContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NavigationItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.count()
    }

    override fun onBindViewHolder(holder: NavigationItemViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)

    }
}