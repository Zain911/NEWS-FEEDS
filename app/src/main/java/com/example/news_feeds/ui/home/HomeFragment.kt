package com.example.news_feeds.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.news_feeds.data.model.Article
import com.example.news_feeds.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val viewModel: HomeViewModel by viewModels()

    private val binding get() = _binding!!
    private lateinit var articlesAdapter: ArticlesAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        articlesAdapter = ArticlesAdapter(arrayListOf()) {
            val action1 = HomeFragmentDirections.actionNavHomeToArticleDetialsFragment(it)
            findNavController().navigate(action1)
        }
        viewModel.articlesList.observe(viewLifecycleOwner) {
            if (it.isNotEmpty())
                articlesAdapter.changeList(it as MutableList<Article>)
        }


        binding.articleRecyclerView.adapter = articlesAdapter
        viewModel.connectionLiveData.observe(viewLifecycleOwner) { isAvailable ->
            if (isAvailable) {
                lifecycleScope.launch {
                    viewModel.getArticles()
                }
            }
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}