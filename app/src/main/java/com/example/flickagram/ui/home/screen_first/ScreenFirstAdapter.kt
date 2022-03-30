package com.example.flickagram.ui.home.screen_first

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flickagram.databinding.ListItemScreenFirstBinding
import com.example.flickagram.domian.model.Photo


class ScreenFirstAdapter (
    private val inflater: LayoutInflater
) : ListAdapter<Photo, ScreenFirstAdapter.PhotoViewHolder>(DiffUtilCallback) {

    object DiffUtilCallback : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }
    }


    inner class PhotoViewHolder(
        private val binding: ListItemScreenFirstBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(photo: Photo) {
            binding.photoItem = photo
            binding.executePendingBindings()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScreenFirstAdapter.PhotoViewHolder {
        return PhotoViewHolder(ListItemScreenFirstBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ScreenFirstAdapter.PhotoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}