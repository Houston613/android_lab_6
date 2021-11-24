package com.example.lab_6_3

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

class MainViewModel: ViewModel(){
    private val url = URL("https://img3.akspic.ru/originals/1/0/5/6/6/166501-mikael_gustafsson_malenkaya_pamyat-oblako-rastenie-atmosfera-voda-3840x2160.jpg")

    var  mIcon_val = MutableLiveData<Bitmap>()

    fun loader(){
        viewModelScope.launch(Dispatchers.IO){
            mIcon_val.postValue(
                BitmapFactory.decodeStream(url.openConnection().getInputStream()))
        }
    }
}