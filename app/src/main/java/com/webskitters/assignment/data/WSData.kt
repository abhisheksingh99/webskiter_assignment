package com.webskitters.assignment.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "ws_table")
@Parcelize
data class WSData(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var email: String,
    var phone_no: String,
    var address: String,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var image: ByteArray?=null
): Parcelable



