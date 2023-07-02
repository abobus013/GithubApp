package com.example.githubapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubapp.data.models.RepositoryPayloadData
import com.example.githubapp.databinding.ItemLayoutBinding

class GithubAdapter : RecyclerView.Adapter<GithubAdapter.GitViewHolder>() {

    var models = mutableListOf<RepositoryPayloadData>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GitViewHolder(binding)
    }

    override fun getItemCount() = models.size

    override fun onBindViewHolder(holder: GitViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class GitViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val data = models[position]

            Glide.with(binding.root.context)
                .load(data.profileData.avatar_url)
                .into(binding.ivPhoto)

            binding.tvName.text = data.profileData.login
            binding.tvAppName.text = data.name

        }
    }
}