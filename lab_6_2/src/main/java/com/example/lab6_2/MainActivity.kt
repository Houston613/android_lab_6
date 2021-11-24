package com.example.lab6_2

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab6_2.databinding.ActivirtyMainBinding
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private lateinit var executor: ExecutorService
    private lateinit var binding: ActivirtyMainBinding
    private var mIcon_val: Bitmap? = null
    private val url = URL("https://img3.akspic.ru/originals/1/0/5/6/6/166501-mikael_gustafsson_malenkaya_pamyat-oblako-rastenie-atmosfera-voda-3840x2160.jpg")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivirtyMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        executor = Executors.newFixedThreadPool(1)
        executor.execute{
            mIcon_val = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            binding.imageView.post{
                binding.imageView.setImageBitmap(mIcon_val)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        executor.shutdown()
    }

}