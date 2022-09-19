package com.example.ecommerce.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entities for database table
 */

@Entity(tableName = "product")
class ProductEntity(
    @ColumnInfo(name = "image") var image: String?,
    @ColumnInfo(name = "title") var title: String?,
    @ColumnInfo(name = "price") var price: String?,
    @ColumnInfo(name = "specialPrice") var specialPrice: String?
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}
