package com.example.mvvm.presentation.ui.browse

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import com.example.mvvm.R
import com.example.mvvm.data.database.models.UserClass
import com.example.mvvm.databinding.FragmentBrowseBinding
import com.example.mvvm.presentation.utils.MotionToast
import com.example.mvvm.presentation.utils.MotionToastStyle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BrowseFragment : Fragment() {

    private val mViewModel: BrowseViewModel by viewModels()

    private lateinit var binding: FragmentBrowseBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBrowseBinding.inflate(layoutInflater)

        setupObserver()
        return binding.root

    }

    private fun setupObserver() {
        mViewModel.getUser.observe(viewLifecycleOwner) { response ->
            val data = response as ArrayList<UserClass>
            if (data.isNotEmpty()) {
                MotionToast.createToast(
                    requireActivity(),
                    getString(R.string.success),
                    data.size.toString(),
                    MotionToastStyle.INFO,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION,
                    ResourcesCompat.getFont(requireActivity(), R.font.helvetica_regular))
            } else {
                MotionToast.createToast(
                    requireActivity(),
                    getString(R.string.error),
                    "No data",
                    MotionToastStyle.ERROR,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION,
                    ResourcesCompat.getFont(requireActivity(), R.font.helvetica_regular))
            }
        }
    }
}