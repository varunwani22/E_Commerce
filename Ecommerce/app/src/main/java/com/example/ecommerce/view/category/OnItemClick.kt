package com.example.ecommerce.view.category

import com.example.ecommerce.model.ResponseModelItem

interface OnItemClick {
    fun onClickOfItem(responseModelItem: ResponseModelItem, position: Int)
}