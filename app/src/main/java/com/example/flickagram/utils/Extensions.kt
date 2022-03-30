package com.example.flickagram.utils

import android.app.Activity
import android.content.Intent

fun Activity.shareUrl(url : String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, url)
    }

    startActivity(Intent.createChooser(intent, "Share Image Link"))
}
