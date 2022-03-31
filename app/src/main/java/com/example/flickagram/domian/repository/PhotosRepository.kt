package com.example.flickagram.domian.repository

import com.example.flickagram.data.database.PhotoDatabase
import com.example.flickagram.domian.model.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PhotosRepository @Inject constructor(private val database:PhotoDatabase) {

    fun getPhotosDataFromDB() =database.photoDao().getAllPhoto()

    suspend fun insertPhotoDataIntoDB(photo : Photo){
        withContext(Dispatchers.IO){
            database.photoDao().insertPhoto(photo)
        }
    }

}