package com.example.lab_6_4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.lab_6_4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this)
            .asBitmap()
            .load("https://img3.akspic.ru/originals/1/0/5/6/6/166501-mikael_gustafsson_malenkaya_pamyat-oblako-rastenie-atmosfera-voda-3840x2160.jpg")
            .into(binding.imageView)
    }
}