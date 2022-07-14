package com.example.news_feeds.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.example.Article
import com.example.news_feeds.R
import com.example.news_feeds.databinding.ItemArticleBinding
import com.example.news_feeds.domain.util.Util


class ArticlesAdapter(
    private var articlesList: MutableList<Article>,
    private val itemClick: (Article) -> Unit,
) :
    RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {
    private lateinit var context: Context

    @SuppressLint("NotifyDataSetChanged")
    fun changeList(newList: MutableList<Article>) {
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
        holder.view.authorByTextView.text =
            context.getString(R.string.by) + articlesList[position].author

        holder.view.publishTimeTextView.text = Util.dateFormat(articlesList[position].publishedAt.toString())

        holder.view.titleTextView.text = articlesList[position].title
        Glide.with(holder.view.articleCompatImageView.context)
            .load(articlesList[position].urlToImage)
            .placeholder(R.drawable.placeholder)
            .into(holder.view.articleCompatImageView)

        holder.view.mainContainerConstraintLayout.setOnClickListener {
            itemClick(articlesList[position])
        }
    }


    override fun getItemCount() = articlesList.size
}
