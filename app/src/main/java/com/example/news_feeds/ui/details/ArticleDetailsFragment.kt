package com.example.news_feeds.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.news_feeds.data.model.Article
import com.example.news_feeds.R
import com.example.news_feeds.databinding.FragmentArticleDetailsBinding
import com.example.news_feeds.domain.util.Util

class ArticleDetailsFragment : Fragment() {

    private var _binding: FragmentArticleDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: ArticleDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentArticleDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setArticleUI(args.articleDetails)
        binding.openWebsiteCompatButton.setOnClickListener {
            openWebsite(args.articleDetails.url)
        }

        return root
    }

    private fun setArticleUI(article: Article) {
        binding.titleTextView.text = article.title
        binding.descriptionTextView.text = article.description
        binding.publishAtTextView.text = Util.dateFormat(article.publishedAt.toString())
        binding.authorByTextView.text = "${context?.getString(R.string.by)}${article.author}"

        Glide.with(binding.articleCompatImageView.context)
            .load(article.urlToImage)
            .placeholder(R.drawable.placeholder)
            .into(binding.articleCompatImageView)

    }
    private fun openWebsite(url: String?) {
        val defaultBrowser =
            Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER)
        defaultBrowser.data = Uri.parse(url)
        startActivity(defaultBrowser)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}