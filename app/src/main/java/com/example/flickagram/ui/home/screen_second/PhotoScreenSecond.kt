package com.example.flickagram.ui.home.screen_second

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.example.flickagram.R
import com.example.flickagram.databinding.FragmentPhotoScreenSecondBinding
import com.example.flickagram.domian.model.Photo
import com.example.flickagram.utils.shareUrl
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream

@AndroidEntryPoint
class PhotoScreenSecond : Fragment(R.layout.fragment_photo_screen_second) {

    private lateinit var photo: Photo
    private lateinit var binding: FragmentPhotoScreenSecondBinding
    private var imageFilePath: String? = null

    companion object {
        fun getInstance(photo: Photo): PhotoScreenSecond {
            val fragment = PhotoScreenSecond()
            fragment.photo = photo
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPhotoScreenSecondBinding.bind(view)
        binding.lifecycleOwner = this

        binding.photoItem = photo

        binding.shareLinkText.setOnClickListener {
            requireActivity().shareUrl(photo.url ?: "")
        }

        binding.shareImageText.setOnClickListener { imageFilePath?.let { shareImage() } }

        loadImage()
    }

    private fun loadImage() {
        Glide.with(binding.thumbnail)
            .asBitmap()
            .load(photo.url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
            .into(object : BitmapImageViewTarget(binding.thumbnail) {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    super.onResourceReady(resource, transition)
                    storeImageInCache(resource)
                }
            })
    }

    private fun storeImageInCache(bitmap: Bitmap) {
        try {
            val cacheDir = requireActivity().cacheDir
            var path = cacheDir.absolutePath + "/photos/"
            val photoDir = File(path)
            if (!photoDir.exists()) photoDir.mkdirs()

            path += "${photo.id}.jpg"
            val imageFile = File(path)

            val stream = FileOutputStream(imageFile)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.close()

            imageFilePath = imageFile.absolutePath
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getContentTypeImagePath(): Uri {
        return FileProvider.getUriForFile(
            requireContext(),
            "com.example.flickagram.fileprovider",
            File(imageFilePath!!)
        )
    }

    private fun shareImage() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_STREAM, getContentTypeImagePath())
        requireContext().startActivity(intent)
    }


}