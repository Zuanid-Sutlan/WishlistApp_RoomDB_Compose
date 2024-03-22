package com.example.wishlistapp_roomdb_compose.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WishDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertWish(wishEntity: Wish)

    // there is no need to make it suspend fun because we are using flow in it which has similar work
    @Query("select * from `wish-table`")
    abstract fun getAllWishes(): Flow<List<Wish>>

    @Update
    abstract suspend fun updateWish(wishEntity: Wish)

    @Delete
    abstract suspend fun deleteWish(wishEntity: Wish)

    // there is no need to make it suspend fun because we are using flow in it which has similar work
    @Query("select * from `wish-table` where id=:id")
    abstract fun getWishById(id: Long): Flow<Wish>

}