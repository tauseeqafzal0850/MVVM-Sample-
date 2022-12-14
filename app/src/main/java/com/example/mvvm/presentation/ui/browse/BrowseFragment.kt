package com.example.mvvm.presentation.ui.browse

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm.R
import com.example.mvvm.data.models.UserClass
import com.example.mvvm.databinding.FragmentBrowseBinding
import com.example.mvvm.presentation.ui.browse.adapter.UserListAdapter
import com.example.mvvm.presentation.ui.browse.viewModel.BrowseViewModel
import com.example.mvvm.presentation.utils.MotionToast
import com.example.mvvm.presentation.utils.MotionToastStyle
import com.example.mvvm.presentation.utils.ViewUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BrowseFragment : Fragment() {

    private val mViewModel: BrowseViewModel by viewModels()
    private lateinit var binding: FragmentBrowseBinding
    private lateinit var adapter: UserListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBrowseBinding.inflate(layoutInflater)
        setupObserver()
        handleEventListeners()
        getUsersList()
        return binding.root

    }

    private fun handleEventListeners() {

        binding.btnSaveUser.setOnClickListener {

            if (binding.edName.text!!.isEmpty()) {
                MotionToast.createToast(
                    requireActivity(),
                    getString(R.string.error),
                    "Enter Your Name",
                    MotionToastStyle.ERROR,
                    MotionToast.GRAVITY_BOTTOM,
                    MotionToast.LONG_DURATION,
                    ResourcesCompat.getFont(requireActivity(), R.font.helvetica_regular)
                )
            } else if (binding.edAge.text!!.isEmpty()) {
                ViewUtils.showToast(
                    requireActivity(),
                    getString(R.string.error),
                    "Enter Your Name",
                    MotionToastStyle.ERROR
                )

            } else if (binding.edDesignation.text!!.isEmpty()) {
                ViewUtils.showToast(
                    requireActivity(),
                    getString(R.string.error),
                    "Enter Your Age",
                    MotionToastStyle.ERROR
                )
            } else {
                saveUser(
                    binding.edName.text.toString(),
                    binding.edAge.text.toString().toInt(),
                    binding.edDesignation.text.toString()
                )
            }
        }
    }

    private fun saveUser(name: String, age: Int, designation: String) {
        lifecycleScope.launch {
            mViewModel.saveUser(UserClass(name, designation, age, 0))
            binding.edName.setText("")
            binding.edAge.setText("")
            binding.edDesignation.setText("")
        }
        getUsersList()
    }

    private fun getUsersList() {
        lifecycleScope.launch {
            mViewModel.getUsersList()
        }
    }

    private fun setupObserver() {

        mViewModel.getUsersList.observe(viewLifecycleOwner) { response ->
            if (response.data.data != null) {
                val data = response.data.data as ArrayList<UserClass>
                setAdapter(data)
            } else {
                if(response.data.message!!.isNotEmpty()) {
                    ViewUtils.showToast(
                        requireActivity(),
                        getString(R.string.error),
                        response.data.message.toString(),
                        MotionToastStyle.ERROR
                    )
                }
            }
        }

        mViewModel.saveUser.observe(viewLifecycleOwner) { response ->
            if (response.data.message == "Employee Saved Successfully") {
                ViewUtils.showToast(
                    requireActivity(),
                    getString(R.string.success),
                    response.data.message.toString(),
                    MotionToastStyle.SUCCESS
                )

            } else {
                if(response.data.message!!.isNotEmpty()) {
                    ViewUtils.showToast(
                        requireActivity(),
                        getString(R.string.error),
                        response.data.message.toString(),
                        MotionToastStyle.ERROR
                    )
                }
            }
        }
    }

    private fun setAdapter(data: java.util.ArrayList<UserClass>) {
        adapter = UserListAdapter(data)
        binding.recyclerViewListUser.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerViewListUser.adapter = adapter
    }
}