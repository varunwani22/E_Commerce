package com.example.ecommerce.view.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.ecommerce.data.Repository
import com.example.ecommerce.local.ProductEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    //adding task to table
    fun insertRecord(productEntity: ProductEntity) {
        repository.insertRecord(productEntity)
    }

    //getting data from table
    fun getAllRecord(): LiveData<List<ProductEntity>> {
        return repository.getAllRecord()
    }

    //deleting existing data
    fun deleteRecord(productEntity: ProductEntity) {
        repository.deleteRecord(productEntity)
    }

    fun getTotal(): LiveData<ProductEntity> {
        return repository.getTotal()
    }
}