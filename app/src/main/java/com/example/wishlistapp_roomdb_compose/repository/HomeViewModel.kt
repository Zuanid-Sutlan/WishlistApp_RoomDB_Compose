package com.example.wishlistapp_roomdb_compose.repository

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wishlistapp_roomdb_compose.Graph
import com.example.wishlistapp_roomdb_compose.data.Wish
import com.example.wishlistapp_roomdb_compose.data.WishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HomeViewModel(private val wishRepository: WishRepository = Graph.wishRepository) : ViewModel() {

    private val _title = mutableStateOf("")
    val title : State<String> = _title

    private val _desc = mutableStateOf("")
    val desc : State<String> = _desc

    private val _dataList = mutableListOf<Wish>()
    val dataList = _dataList

    fun updateTitle(newTitle: String){
        _title.value = newTitle
    }

    fun updateDesc(newDesc: String){
        _desc.value = newDesc
    }

    fun addWishInList(wish: Wish){
        _dataList.add(wish)
    }

    private lateinit var _allWishes: Flow<List<Wish>>

    init {
        viewModelScope.launch {
            _allWishes = wishRepository.getAllWishes()
        }
    }
    val allWishes: Flow<List<Wish>> = _allWishes

    fun insertWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.insertWish(wish)
        }
    }

    fun updateWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.updateWish(wish)
        }
    }

    fun deleteWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.deleteWish(wish)
        }
    }

    fun getWishById(id: Long): Flow<Wish>{
        return wishRepository.getWishById(id)
    }


}