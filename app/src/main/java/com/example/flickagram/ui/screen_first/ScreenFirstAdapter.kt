package com.example.flickagram.ui.screen1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flickagram.databinding.ListItemScreen1Binding
import com.example.flickagram.domian.model.Photos

class Screen1Adapter (
    private val inflater: LayoutInflater
) : ListAdapter<Photos, Screen1Adapter.PhotoViewHolder>(DiffUtilCallback) {

    object DiffUtilCallback : DiffUtil.ItemCallback<Photos>() {
        override fun areItemsTheSame(oldItem: Photos, newItem: Photos): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photos, newItem: Photos): Boolean {
            return oldItem == newItem
        }
    }


    inner class PhotoViewHolder(
        private val binding: ListItemScreen1Binding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(photos: Photos) {
            binding.photoItem = photos
            binding.executePendingBindings()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Screen1Adapter.PhotoViewHolder {
        return PhotoViewHolder(ListItemScreen1Binding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: Screen1Adapter.PhotoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}