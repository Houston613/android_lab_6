package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivityE : AppCompatActivity() {
    private var secondsElapsed: Int = 0
    private val tag = "MainActivity"
    private lateinit var textSecondsElapsed: TextView
    private lateinit var executorService: ExecutorService
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
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
        Log.i(tag, "MainActivity: created")
    }

    override fun onStop() {
        super.onStop()
        executorService.shutdown()
        Log.i(tag, "Thread stopped")
        Log.i(tag, "MainActivity: stopped")
    }

    override fun onStart() {
        super.onStart()
        executorService = Executors.newFixedThreadPool(1)
        executorService.execute {
            while (!executorService.isShutdown) {
                Thread.sleep(1000)
                textSecondsElapsed.post {
                    textSecondsElapsed.text = getString(R.string.second_elapsed, secondsElapsed++)
                }
            }
        }
        Log.i(tag, "Thread started")
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