package com.example.mvvm.presentation.ui.browse.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.data.models.UserClass
import com.example.mvvm.databinding.UserItemContentBinding


class UserListAdapter(private val dataList: ArrayList<UserClass>) :
    RecyclerView.Adapter<UserListAdapter.NavigationItemViewHolder>() {

    private lateinit var context: Context

    class NavigationItemViewHolder(private val binding: UserItemContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserClass) {

            binding.tvName.text = buildString {
                append("Name: ")
                append(user.name)
            }
            binding.tvDesignation.text = buildString {
                append("Designation: ")
                append(user.designation)
            }
            binding.tvAge.text = buildString {
                append("Age: ")
                append(user.age)
            }



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationItemViewHolder {
        context = parent.context
        val binding =
            UserItemContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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