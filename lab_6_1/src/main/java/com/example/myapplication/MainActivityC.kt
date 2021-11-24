package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MainActivityC : AppCompatActivity() {
    private var secondsElapsed: Int = 0
    private val tag = "MainActivity"
    private lateinit var textSecondsElapsed: TextView
    private lateinit var binding: ActivityMainBinding


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.run { putInt("timeStop", secondsElapsed) }
        Log.i(tag, "MainActivity InstanceSaved")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.run { secondsElapsed = getInt("timeStop") }
        Log.i(tag, "MainActivity InstanceRestore")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(
            binding.root
        )
        textSecondsElapsed = binding.textSecondsElapsed
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                while (isActive) {
                    delay(1000)
                    binding.textSecondsElapsed.post {
                        textSecondsElapsed.text =
                            getString(R.string.second_elapsed, secondsElapsed++)
                    }
                }
            }
            Log.i(tag, "MainActivity: created")
        }
    }

        override fun onStop() {
            super.onStop()

            Log.i(tag, "MainActivity: stopped")
        }

        override fun onStart() {
            super.onStart()
            Log.i(tag, "MainActivity: started")
        }


        override fun onPause() {
            super.onPause()
            Log.i(tag, "MainActivity: Paused")

        }

        override fun onResume() {
            super.onResume()
            Log.i(tag, "MainActivity: resumed")
        }


        override fun onDestroy() {
            super.onDestroy()
            Log.i(tag, "MainActivity: destroyed")
        }
    }