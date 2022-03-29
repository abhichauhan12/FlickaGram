package com.example.flickagram.ui.screen1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.flickagram.domian.repository.NetworkRepository

class Screen1ViewModel(private val networkRepository: NetworkRepository) : ViewModel(){

    companion object{
        fun get(owner: ViewModelStoreOwner) : Screen1ViewModel {
            return ViewModelProvider(owner, Factory(NetworkRepository.get()))[Screen1ViewModel::class.java]
        }
    }


    class Factory(private val networkRepository: NetworkRepository) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return Screen1ViewModel(networkRepository) as T
        }
    }
}