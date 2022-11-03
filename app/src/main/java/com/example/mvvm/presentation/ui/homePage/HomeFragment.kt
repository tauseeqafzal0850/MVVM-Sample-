package com.example.mvvm.presentation.ui.homePage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mvvm.R
import com.example.mvvm.databinding.FragmentHomeBinding
import com.example.mvvm.presentation.utils.Constants
import com.example.mvvm.presentation.utils.MotionToast
import com.example.mvvm.presentation.utils.MotionToastStyle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val mViewModel: HomeViewModel by viewModels()
    private val page: Int = 1
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        //  setupObserver()
        //  getPopularMovies()
        return binding.root
    }

    private fun getPopularMovies() {
        mViewModel.getPopularMovies(api_key = Constants.apiKey, "en-US", page)
    }


    private fun setupObserver() {
        mViewModel.getPopularMovies.observe(viewLifecycleOwner) { response ->
            if (!response.isLoading) {
                response.data.let { result ->
                    if (result.results!!.isNotEmpty()) {
                        MotionToast.createToast(
                            requireActivity(),
                            getString(R.string.success),
                            response.data.results!!.size.toString(),
                            MotionToastStyle.INFO,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(requireActivity(), R.font.helvetica_regular)
                        )
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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}