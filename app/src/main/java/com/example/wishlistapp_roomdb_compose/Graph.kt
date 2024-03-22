package com.example.wishlistapp_roomdb_compose

import android.content.Context
import androidx.room.Room
import com.example.wishlistapp_roomdb_compose.data.WishDatabase
import com.example.wishlistapp_roomdb_compose.data.WishRepository

object Graph {
    lateinit var database: WishDatabase

    val wishRepository by lazy { WishRepository(database.wishDao()) }

    fun provide(context: Context){
        database = Room.databaseBuilder(context, WishDatabase::class.java, "wishlist.dp").build()
    }
}