package com.example.ecommerce.local

import androidx.room.*

/**
 * Database schema with version
 */

@Database(
    entities = [ProductEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun getTaskDao(): ProductDAO
}