package com.example.lab_6_3


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.example.lab_6_3.databinding.ActivirtyMainBinding
import kotlinx.coroutines.Job


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivirtyMainBinding
    private var viewModel = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivirtyMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.loader()
        viewModel.myJob

        viewModel.mIcon_val.observe(this) { value ->
            binding.imageView.setImageBitmap(value)
        }
    }
    private fun Job.status(): String = when {
        isCancelled -> "cancelled"
        isActive -> "Active"
        isCompleted -> "Complete"
        else -> "Nothing"
    }

    override fun onStop() {
        Log.i("test","stop  state: ${viewModel.myJob.status()}")

        super.onStop()
    }
    override fun onDestroy() {
        Log.i("test","destroy  state: ${viewModel.myJob.status()}")
        super.onDestroy()
    }
}