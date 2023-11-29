package com.example.githubusersubmission2.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubusersubmission2.data.response.ItemsItem
import com.example.githubusersubmission2.databinding.ItemReviewBinding

class GitHubAdapter : ListAdapter<ItemsItem, GitHubAdapter.MyViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }
    class MyViewHolder(val binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(gituser: ItemsItem){
            binding.tvUsername.text = gituser.login
            Glide.with(binding.root)
                .load(gituser.avatarUrl)
                .into(binding.ivUser)
            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, DetailUserActivity::class.java)
                intent.putExtra(EXTRA_USERNAME, gituser.login)
                binding.root.context.startActivity(intent)
            }
        }
    }
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>() {
            override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
        }
        const val EXTRA_USERNAME = "USERNAME"
    }
}