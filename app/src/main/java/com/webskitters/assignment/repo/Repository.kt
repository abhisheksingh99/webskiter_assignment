package com.webskitters.assignment.repo

import com.webskitters.assignment.api.RetrofitInstance
import com.webskitters.assignment.model.ImageListItem
import retrofit2.Response
import java.util.*

class Repository {

    suspend fun getImageList(): Response<LinkedList<ImageListItem>> {
        return RetrofitInstance.api.getImageList()
    }
}