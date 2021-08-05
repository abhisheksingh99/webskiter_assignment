package com.webskitters.assignment.ui.fragments.user

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.webskitters.assignment.data.WSData
import com.webskitters.assignment.data.WebskitterDB
import com.webskitters.assignment.repo.DBRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val toDoDao = WebskitterDB.getDatabase(
        application
    ).wsDao()
    private val repository: DBRepository = DBRepository(toDoDao)

    val getAllData: LiveData<List<WSData>> = repository.getAllData

    var emptyDatabase: MutableLiveData<Boolean> = MutableLiveData(false)

    fun checkIfDatabaseEmpty(wsData: List<WSData>){
        emptyDatabase.value = wsData.isEmpty()
    }

    fun insertData(toDoData: WSData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(toDoData)
        }
    }

    fun updateData(toDoData: WSData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(toDoData)
        }
    }

    fun deleteItem(toDoData: WSData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(toDoData)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<WSData>>{
        return repository.searchDatabase(searchQuery)
    }

}