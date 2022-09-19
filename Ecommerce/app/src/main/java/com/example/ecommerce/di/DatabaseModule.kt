package com.example.ecommerce.di

import android.content.Context
import androidx.room.Room
import com.example.ecommerce.local.ProductDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     *Initialization of database and dao
     */

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): ProductDatabase = Room.databaseBuilder(
        context,
        ProductDatabase::class.java,
        "product_db"
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: ProductDatabase) = database.getTaskDao()

}