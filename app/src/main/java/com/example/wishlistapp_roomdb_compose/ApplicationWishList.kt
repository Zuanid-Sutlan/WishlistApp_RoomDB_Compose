package com.example.wishlistapp_roomdb_compose

import android.app.Application

class ApplicationWishList: Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}