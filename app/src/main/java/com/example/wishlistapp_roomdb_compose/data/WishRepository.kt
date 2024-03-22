package com.example.wishlistapp_roomdb_compose.data

import kotlinx.coroutines.flow.Flow

class WishRepository(private val wishDao: WishDao) {

    suspend fun insertWish(wishEntity: Wish){
        wishDao.insertWish(wishEntity)
    }

    fun getAllWishes(): Flow<List<Wish>> = wishDao.getAllWishes()

    fun getWishById(id: Long): Flow<Wish>{
        return wishDao.getWishById(id)
    }

    suspend fun updateWish(wishEntity: Wish){
        wishDao.updateWish(wishEntity)
    }

    suspend fun deleteWish(wishEntity: Wish){
        wishDao.deleteWish(wishEntity)
    }
}