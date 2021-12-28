package com.example.lab_6_3

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class MainViewModel: ViewModel(){
    private val url = URL("https://img3.akspic.ru/originals/1/0/5/6/6/166501-mikael_gustafsson_malenkaya_pamyat-oblako-rastenie-atmosfera-voda-3840x2160.jpg")

    var  mIcon_val = MutableLiveData<Bitmap>()
    private lateinit var bm: Bitmap
    public lateinit var myJob: Job

    fun loader(){
        Log.i("test", "loader started")
        myJob = viewModelScope.launch(Dispatchers.IO) {
            Log.i("test", Thread.currentThread().name)
            bm = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            withContext(Dispatchers.Main) {
                Log.i("test", Thread.currentThread().name)
                mIcon_val.value = bm
            }
        }
        Log.i("test","Job  state: ${myJob.status()}")
    }
    private fun Job.status(): String = when {
        isCancelled -> "cancelled"
        isActive -> "Active"
        isCompleted -> "Complete"
        else -> "Nothing"
    }
}