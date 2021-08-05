package com.webskitters.assignment.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "ws_image_table")
@Parcelize
data class ImageListItem(
    @PrimaryKey(autoGenerate = true)
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String,
    var IsSelected: Boolean=false
): Parcelable