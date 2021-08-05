package com.webskitters.assignment.repo

import androidx.lifecycle.LiveData
import com.webskitters.assignment.data.WSDao
import com.webskitters.assignment.data.WSData
import com.webskitters.assignment.model.ImageListItem
import java.util.*

class DBRepository(private val wsDao: WSDao) {
    val getAllData: LiveData<List<WSData>> = wsDao.getAllData()
    val getAllImageData: LiveData<List<ImageListItem>> = wsDao.getAllImage()
    val getSelectCount: LiveData<Int> = wsDao.selectCount()

    suspend fun insertData(toDoData: WSData){
        wsDao.insertData(toDoData)
    }

    suspend fun updateData(toDoData: WSData){
        wsDao.updateData(toDoData)
    }

    suspend fun updateImageData(toDoData: ImageListItem){
        wsDao.updateImageData(toDoData)
    }


    suspend fun insertImageData(toDoData: LinkedList<ImageListItem>){
        wsDao.insertImageData(toDoData)
    }


    suspend fun deleteItem(toDoData: WSData){
        wsDao.deleteItem(toDoData)
    }

    suspend fun deleteAll(){
        wsDao.deleteAll()
    }

    fun searchDatabase(searchQuery: String): LiveData<List<WSData>> {
        return wsDao.searchDatabase(searchQuery)
    }

}