package com.example.ecommerce.data

import javax.inject.Inject

/**
 * Function to call api
 */
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getListOfCategory() = apiService.getCategories()
}