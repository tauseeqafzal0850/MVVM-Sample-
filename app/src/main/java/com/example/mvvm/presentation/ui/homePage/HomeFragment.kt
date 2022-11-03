package com.example.mvvm.presentation.ui.homePage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm.R
import com.example.mvvm.di.responses.NewsResponseDto
import com.example.mvvm.databinding.FragmentHomeBinding
import com.example.mvvm.presentation.ui.homePage.adapter.NewsListAdapter
import com.example.mvvm.presentation.ui.homePage.viewModel.HomeViewModel
import com.example.mvvm.presentation.utils.MotionToast
import com.example.mvvm.presentation.utils.MotionToastStyle
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val mViewModel: HomeViewModel by viewModels()
    private val page: Int = 1
    private lateinit var adapter: NewsListAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        setupObserver()
        getPopularNews()
        return binding.root
    }
    private fun getPopularNews() {
        mViewModel.getPopularNews()
    }

    private fun setupObserver() {
        mViewModel.getPopularNews.observe(viewLifecycleOwner) { response ->
            if (!response.isLoading) {
                response.data.let { result ->
                    if (result.articles!!.isNotEmpty()) {
                        setAdapter(result.articles!!)
                    } else {
                        response.data.let {
                            MotionToast.createToast(
                                requireActivity(),
                                getString(R.string.error),
                                response.message.toString(),
                                MotionToastStyle.ERROR,
                                MotionToast.GRAVITY_BOTTOM,
                                MotionToast.LONG_DURATION,
                                ResourcesCompat.getFont(requireActivity(), R.font.helvetica_regular)
                            )
                        }
                    }
                }

            }
        }
    }

    private fun setAdapter(articles: List<NewsResponseDto.Article>) {
        adapter= NewsListAdapter(articles as ArrayList<NewsResponseDto.Article>)
        binding.recylerViewNews.layoutManager=LinearLayoutManager(requireActivity())
        binding.recylerViewNews.adapter=adapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}