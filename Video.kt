package com.example.photosapplication.Model

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Video(
    var id :Long,
    var name:String,
    var path:String,
    var artUri:Uri
):Parcelable


