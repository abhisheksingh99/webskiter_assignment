package com.webskitters.assignment.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.webskitters.assignment.model.ImageListItem

@Database(entities = [WSData::class,ImageListItem::class], version = 1, exportSchema = false)
abstract class WebskitterDB : RoomDatabase() {

    abstract fun wsDao(): WSDao

    companion object {
        @Volatile
        private var INSTANCE: WebskitterDB? = null

        fun getDatabase(context: Context): WebskitterDB=
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                WebskitterDB::class.java, "webskitter_database"
            ).build()
    }



}