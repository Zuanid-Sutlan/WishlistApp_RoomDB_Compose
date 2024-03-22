package com.example.wishlistapp_roomdb_compose.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Wish::class], version = 1, exportSchema = false)
abstract class WishDatabase: RoomDatabase() {
    abstract fun wishDao(): WishDao
}