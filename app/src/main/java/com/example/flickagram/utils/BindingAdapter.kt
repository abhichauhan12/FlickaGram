package com.example.flickagram.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.flickagram.R

@BindingAdapter("loadImage")
fun loadImageInImageView(imageView: ImageView, url : String?) {
    Glide.with(imageView).load(url)
        .placeholder(R.drawable.ic_launcher_foreground)
        .error(R.drawable.ic_launcher_foreground)
        .into(imageView)

}