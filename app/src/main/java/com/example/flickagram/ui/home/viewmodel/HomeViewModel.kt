package com.example.flickagram.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flickagram.domian.model.Photo
import com.example.flickagram.domian.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val networkRepository: NetworkRepository) :
    ViewModel() {

    private var page = 1
    private var hasNextPage = true

    private val _photosList = MutableStateFlow<List<Photo>>(ArrayList())
    val photoList = _photosList.asStateFlow()

    private val _fetchStatus = MutableStateFlow<FetchStatus?>(null)
    val fetchStatus = _fetchStatus.asStateFlow()

    fun getPhotos() {
        if (hasNextPage) {
            viewModelScope.launch {
                _fetchStatus.value = FetchStatus.LOADING
                withContext(Dispatchers.IO) {
                    val response = networkRepository.getPhotos(page = page)
                    response.data?.let { photos ->
                        hasNextPage = photos.totalPagesCount > photos.currentPage
                        page = photos.currentPage + 1

                        val updatePhotosList = ArrayList<Photo>().apply {
                            addAll(photoList.value)
                            addAll(photos.photoList)
                        }
                        _photosList.value = updatePhotosList
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