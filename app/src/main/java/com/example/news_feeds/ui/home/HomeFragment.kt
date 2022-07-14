package com.example.news_feeds.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.example.Articles
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

        }

        viewModel.articlesList.observe(viewLifecycleOwner) {
            if (it.isNotEmpty())
                articlesAdapter.changeList(it as MutableList<Articles>)
        }
        binding.articleRecyclerView.adapter = articlesAdapter
        viewLifecycleOwner.lifecycleScope.launch {
            val x = viewModel.getArticles()
            Log.d("HomeArticles: ",
                x.toString())
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}