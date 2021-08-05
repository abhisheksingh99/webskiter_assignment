package com.webskitters.assignment.ui.fragments.home

import android.app.Application
import androidx.lifecycle.*
import com.webskitters.assignment.data.WebskitterDB
import com.webskitters.assignment.model.ImageListItem
import com.webskitters.assignment.repo.DBRepository
import com.webskitters.assignment.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.*

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val toDoDao = WebskitterDB.getDatabase(
        application
    ).wsDao()
    private val repositoryx: DBRepository = DBRepository(toDoDao)

    val getAllData: LiveData<List<ImageListItem>> = repositoryx.getAllImageData

    val getCount: LiveData<Int> = repositoryx.getSelectCount

    private val repository: Repository = Repository()
    var myResponse: MutableLiveData<Response<LinkedList<ImageListItem>>> = MutableLiveData()


    fun getImageList(){
        viewModelScope.launch {
            val response = repository.getImageList()
            myResponse.value = response

        }
    }


    fun insertData(toDoData: LinkedList<ImageListItem>) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryx.insertImageData(toDoData)
        }
    }

    fun updateData(toDoData: ImageListItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryx.updateImageData(toDoData)
        }
    }

}