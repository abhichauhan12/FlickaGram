package com.example.flickagram.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flickagram.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

    }
}