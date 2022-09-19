package com.example.ecommerce.data

import com.example.ecommerce.model.ResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    /**
     * api  client to call an api with endpoint
     */
    @GET("getpr.php")
    suspend fun getCategories(): Response<ResponseModel>
}