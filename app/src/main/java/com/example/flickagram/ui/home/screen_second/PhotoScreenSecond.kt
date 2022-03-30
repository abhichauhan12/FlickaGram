package com.example.flickagram.ui.home.screen_second

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.example.flickagram.R
import com.example.flickagram.databinding.FragmentPhotoScreenSecondBinding
import com.example.flickagram.domian.model.Photo
import dagger.hilt.android.AndroidEntryPoint
import java.io.File


@AndroidEntryPoint
class PhotoScreenSecond() : Fragment(R.layout.fragment_photo_screen_second) {

    private lateinit var photo : Photo
    private lateinit var binding : FragmentPhotoScreenSecondBinding

    companion object{
        fun getInstance(photo: Photo): PhotoScreenSecond {
            val fragment =PhotoScreenSecond()
            fragment.photo = photo
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentPhotoScreenSecondBinding.bind(view)
        binding.lifecycleOwner=this

//        val photo =requireArguments().getParcelable<Photo>("photo")

//        vation = requireArguments().getInt("position")

        binding.photoItem=photo


        binding.shareImageText.setOnClickListener {
//            sharePhoto()
        }

    }

//    private fun pathOfImage(): String{
//        val dirs = requireContext().getExternalFilesDirs(null)
//        val imagePath = dirs[0].absolutePath+"/.jpg"
//        val file = File(imagePath)
//
//        return file.absolutePath
//    }
//
//    private fun getContentTypeImagePath(path: String): Uri {
//        return FileProvider.getUriForFile(requireContext(), "com.example.flickagram.fileprovider", File(path))
//    }
//
//    private fun sharePhoto() {
//        val path =pathOfImage()
//        val uri =getContentTypeImagePath(path)
//
//        val intent = Intent(Intent.ACTION_SEND)
//        intent.type = "image/*"
//        intent.putExtra(Intent.EXTRA_STREAM, uri)
//        requireContext().startActivity(intent)
//    }




}