package com.example.ecommerce.view.cart

import com.example.ecommerce.local.ProductEntity

interface OnClickDelete {
    fun onDeleteListener(product: ProductEntity)
}