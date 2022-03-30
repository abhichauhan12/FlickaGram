package com.example.flickagram.domian.repository

import com.example.flickagram.data.network.api.PhotosAPI
import com.example.flickagram.ui.home.viewmodel.FetchStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.example.flickagram.domian.core.Result
import com.example.flickagram.domian.model.Photos


class NetworkRepository @Inject constructor(private val photosAPI: PhotosAPI) {

    suspend fun getPhotos(page : Int): Result<Photos> {
        return withContext(Dispatchers.IO) {
            try {
                val response = photosAPI.getImages(page=page).execute()
                if (response.isSuccessful) Result(FetchStatus.SUCCESS, response.body()?.photos)
                else Result(FetchStatus.FAILURE, null)
            } catch (e: Exception) {
                Result(FetchStatus.FAILURE, null)
            }
        }
    }
}
