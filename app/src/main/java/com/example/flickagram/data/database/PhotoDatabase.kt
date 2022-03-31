package com.example.flickagram.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.flickagram.data.database.dao.PhotoDao
import com.example.flickagram.domian.model.Photo

@Database(entities = [Photo::class], version = 1,exportSchema = true)
abstract class PhotoDatabase : RoomDatabase(){

    abstract fun photoDao() :PhotoDao

}