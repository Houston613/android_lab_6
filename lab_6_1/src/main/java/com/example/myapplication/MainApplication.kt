package com.example.myapplication

import android.app.Application
import android.util.Log
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainApplication : Application() {
    init {
        Log.i("test","poll created")
    }
    val executor: ExecutorService = Executors.newFixedThreadPool(1)
}