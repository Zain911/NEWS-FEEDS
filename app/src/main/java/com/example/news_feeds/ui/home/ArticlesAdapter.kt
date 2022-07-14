package com.example.news_feeds.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.example.Articles
import com.example.news_feeds.R
import com.example.news_feeds.databinding.ItemArticleBinding
import java.text.SimpleDateFormat


class ArticlesAdapter(
    private var articlesList: MutableList<Articles>,
    private val itemClick: (Articles) -> Unit,
) :
    RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {
    private lateinit var context: Context

    @SuppressLint("NotifyDataSetChanged")
    fun changeList(newList: MutableList<Articles>) {
        articlesList.clear()
        articlesList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return ViewHolder(binding)
    }

    class ViewHolder(var view: ItemArticleBinding) : RecyclerView.ViewHolder(view.root)

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.publishByTextView.text = articlesList[position].author

       // val format = SimpleDateFormat("MMMM d ,yyyy")
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

        val date = format.parse(articlesList[position].publishedAt.toString())

        holder.view.publishTimeTextView.text = date?.toString()

        holder.view.titleTextView.text =
            context.getString(R.string.by) + articlesList[position].title
        Glide.with(holder.view.articleCompatImageView.context)
            .load(articlesList[position].urlToImage)
            .into(holder.view.articleCompatImageView)

        holder.view.mainContainerConstraintLayout.setOnClickListener {
            itemClick(articlesList[position])
        }
    }


    override fun getItemCount() = articlesList.size
}
