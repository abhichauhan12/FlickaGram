package com.example.flickagram.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flickagram.domian.model.Photo
import com.example.flickagram.domian.repository.PhotosRepository
import com.example.flickagram.domian.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val photosRepository: PhotosRepository
    ) :
    ViewModel() {

    val photos =photosRepository.getPhotosDataFromDB().stateIn(viewModelScope, SharingStarted.Lazily,ArrayList())


    private var page = 1
    var hasNextPage = true


    private val _fetchStatus = MutableStateFlow<FetchStatus?>(null)
    val fetchStatus = _fetchStatus.asStateFlow()

    fun getPhotos() {
        if (hasNextPage && _fetchStatus.value != FetchStatus.LOADING) {
            viewModelScope.launch {
                _fetchStatus.value = FetchStatus.LOADING
                withContext(Dispatchers.IO) {
                    val response = networkRepository.getPhotos(page = page)
                    response.data?.let { photos ->
                        hasNextPage = photos.totalPagesCount > photos.currentPage
                        page = photos.currentPage + 1

                        photos.photoList.forEach {
                            photosRepository.insertPhotoDataIntoDB(it)
                        }

                        _fetchStatus.value = FetchStatus.SUCCESS
                    }
                }
            }
        }
    }
}


enum class FetchStatus {
    LOADING,
    SUCCESS,
    FAILURE
}