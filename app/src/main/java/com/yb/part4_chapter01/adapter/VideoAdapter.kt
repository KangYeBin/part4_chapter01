package com.yb.part4_chapter01.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yb.part4_chapter01.R
import com.yb.part4_chapter01.databinding.ItemVideoBinding
import com.yb.part4_chapter01.model.VideoModel

class VideoAdapter(val callback: (String, String) -> Unit): ListAdapter<VideoModel, VideoAdapter.ViewHolder>(diffUtil) {
    private var binding: ItemVideoBinding? = null

    inner class ViewHolder(private val view: View) :RecyclerView.ViewHolder(view) {

        fun bind(videoModel: VideoModel) {
            val itemVideoBinding = ItemVideoBinding.bind(view)
            binding = itemVideoBinding

            val titleTextView = itemVideoBinding.titleTextView
            val subtitleTextView = itemVideoBinding.subtitleTextView
            val thumbnailImageView = itemVideoBinding.thumbnailImageView

            titleTextView.text = videoModel.title
            subtitleTextView.text = videoModel.subtitle

            Glide.with(thumbnailImageView.context)
                .load(videoModel.thumb)
                .into(thumbnailImageView)

            view.setOnClickListener {
                callback(videoModel.source, videoModel.title)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<VideoModel>(){
            override fun areItemsTheSame(oldItem: VideoModel, newItem: VideoModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: VideoModel, newItem: VideoModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}