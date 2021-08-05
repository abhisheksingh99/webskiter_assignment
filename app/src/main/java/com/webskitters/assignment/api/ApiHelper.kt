package com.webskitters.assignment.api

import com.webskitters.assignment.model.ImageListItem
import retrofit2.Response

import retrofit2.http.GET
import java.util.*

interface ApiHelper {

    @GET("photos")
    suspend fun getImageList(): Response<LinkedList<ImageListItem>>


}