package com.example.ecommerce.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductDAO {

    /**
     * Dao class with operation methods
     */

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(productEntity: ProductEntity)

    @Query("select * from product")
    fun getTask(): LiveData<List<ProductEntity>>

    @Delete
    fun deleteTask(productEntity: ProductEntity)

    @Query("select sum(specialPrice) from product")
    fun getTotal(): LiveData<ProductEntity>
}