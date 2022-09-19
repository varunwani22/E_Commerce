package com.example.ecommerce.view.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerce.data.Repository
import com.example.ecommerce.model.ResponseModel
import com.example.ecommerce.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _responseLiveData: MutableLiveData<NetworkResult<ResponseModel>> =
        MutableLiveData()
    val responseLiveData: LiveData<NetworkResult<ResponseModel>> = _responseLiveData
    fun getListOfCategory() = viewModelScope.launch {
        _responseLiveData.value = NetworkResult.Loading()
        repository.getListOfCategory().collect {
            _responseLiveData.value = it
        }
    }

}