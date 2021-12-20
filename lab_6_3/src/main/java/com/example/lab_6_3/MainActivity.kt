package com.example.lab_6_3


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab_6_3.databinding.ActivirtyMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivirtyMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = MainViewModel()

        binding = ActivirtyMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.loader()
        viewModel.mIcon_val.observe(this) { value ->
            binding.imageView.setImageBitmap(value)
        }
    }
}