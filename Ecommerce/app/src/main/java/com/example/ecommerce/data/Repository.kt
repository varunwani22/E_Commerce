package com.example.ecommerce.data

import androidx.lifecycle.LiveData
import com.example.ecommerce.local.ProductDAO
import com.example.ecommerce.local.ProductEntity
import com.example.ecommerce.model.ResponseModel
import com.example.ecommerce.utils.BaseApiResponse
import com.example.ecommerce.utils.NetworkResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource, private val productDAO: ProductDAO
) : BaseApiResponse() {
    /**
     * Repository with model class
     */
    suspend fun getListOfCategory(): Flow<NetworkResult<ResponseModel>> {
        return flow {
            emit(safeApiCall {
                remoteDataSource.getListOfCategory()
            })
        }
    }

    /**
     * Database functions
     */

    //getting data from table
    fun getAllRecord(): LiveData<List<ProductEntity>> {
        return productDAO.getTask()
    }

    //accessing data from Dao
    fun insertRecord(productEntity: ProductEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            productDAO.addTask(productEntity)
        }
    }

    fun deleteRecord(productEntity: ProductEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            productDAO.deleteTask(productEntity)
        }
    }

    fun getTotal(): LiveData<ProductEntity> {
        return productDAO.getTotal()
    }
}