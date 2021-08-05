package com.webskitters.assignment.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.webskitters.assignment.model.ImageListItem
import java.util.*

@Dao
interface WSDao {

    @Query("SELECT * FROM ws_table ORDER BY id ASC")
    fun getAllData(): LiveData<List<WSData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(toDoData: WSData)

    @Update
    suspend fun updateData(toDoData: WSData)

    @Delete
    suspend fun deleteItem(toDoData: WSData)

    @Query("DELETE FROM ws_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM ws_table WHERE name LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<WSData>>

    @Query("SELECT * FROM ws_image_table ORDER BY id ASC")
    fun getAllImage(): LiveData<List<ImageListItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertImageData(toDoData: LinkedList<ImageListItem>)

    @Update
    suspend fun updateImageData(toDoData: ImageListItem)

    @Query("SELECT COUNT(*) FROM ws_image_table WHERE IsSelected = 1")
    fun selectCount(): LiveData<Int>




}