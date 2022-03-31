package com.example.flickagram.domian.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "photo_data")
@Parcelize
data class Photo(

    @ColumnInfo(name = "id")
    @PrimaryKey
    @SerializedName("id")
    val id : Long,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title : String,

    @ColumnInfo(name = "height")
    @SerializedName("height_h")
    val height : Int,

    @ColumnInfo(name = "width")
    @SerializedName("width_h")
    val width : Int,

    @ColumnInfo(name = "url")
    @SerializedName("url_h")
    val url : String?

) : Parcelable

@Parcelize
data class Photos(
    @SerializedName("photo")
    var photoList : List<Photo>,
    @SerializedName("page")
    val currentPage : Int,
    @SerializedName("pages")
    val totalPagesCount : Int
): Parcelable

data class PhotosBody(
    @SerializedName("photos")
    val photos : Photos
)