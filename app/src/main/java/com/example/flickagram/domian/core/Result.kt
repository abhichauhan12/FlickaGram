package com.example.flickagram.domian.core

import com.example.flickagram.ui.home.viewmodel.FetchStatus

data class Result<T>(val status : FetchStatus, val data: T?)