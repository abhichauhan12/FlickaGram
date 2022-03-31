package com.example.flickagram.ui.home.screen_first

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flickagram.databinding.ListItemScreenFirstBinding
import com.example.flickagram.domian.model.Photo


class ScreenFirstAdapter (
    private val onItemClick :(Int) -> Unit
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

        init {
            binding.itemContainer.setOnClickListener {
                val position : Int = adapterPosition
                onItemClick(position)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScreenFirstAdapter.PhotoViewHolder {
        return PhotoViewHolder(ListItemScreenFirstBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ScreenFirstAdapter.PhotoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}