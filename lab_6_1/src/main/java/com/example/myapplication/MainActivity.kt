package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var secondsElapsed: Int = 0
    private val tag = "MainActivity"
    private lateinit var textSecondsElapsed: TextView
    private lateinit var backgroundThread: Thread
     override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.run {putInt("timeStop",secondsElapsed)}
        Log.i(tag,"MainActivity InstanceSaved")
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.run { secondsElapsed = getInt("timeStop")}
        Log.i(tag,"MainActivity InstanceRestore")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
        Log.i(tag, "MainActivity: created")
    }

    override fun onStop() {
        super.onStop()
        backgroundThread.interrupt()
        Log.i(tag, "Thread stopped")
        Log.i(tag, "MainActivity: stopped")
    }
    override fun onStart() {
        super.onStart()
        backgroundThread = Thread {
            while (!Thread.currentThread().isInterrupted) {
                try {
                    Thread.sleep(1000)
                    textSecondsElapsed.post {
                        textSecondsElapsed.text = getString(R.string.second_elapsed, secondsElapsed++)
                    }
                } catch (exception: InterruptedException){
                    Thread.currentThread().interrupt()
                }
            }
        }
        backgroundThread.start()
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