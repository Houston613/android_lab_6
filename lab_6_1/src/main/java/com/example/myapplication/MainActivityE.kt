package com.example.myapplication

import android.app.Application
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.FutureTask

class MainActivityE : AppCompatActivity() {
    private var secondsElapsed: Int = 0
    private val tag = "MainActivity"
    private lateinit var textSecondsElapsed: TextView
    private lateinit var execFuture: Future<*>
    private lateinit var executorService: ExecutorService
    private lateinit var futureTask: FutureTask<Any>

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
        execFuture.cancel(true)
        Log.i(tag, "MainActivity: stopped")
    }

    override fun onStart() {
        super.onStart()
        executorService = (application as MainApplication).executor
            Log.d(tag, "${Thread.currentThread()} execute")
            execFuture = executorService.submit {


                Log.d(tag, "${Thread.currentThread()} submit")
                (application as MainApplication).counter =
                    (application as MainApplication).counter + 1
                val temp = (application as MainApplication).counter
                Log.i(tag, "thread started $temp")


                while (!execFuture.isCancelled) {
                    Thread.sleep(1000)
                    textSecondsElapsed.post {
                        textSecondsElapsed.text =
                            getString(R.string.second_elapsed, ++secondsElapsed)
                    }
                    Log.i(tag, "working")
                }
                Log.i(tag, "cancelled")

            }
        Log.i(tag, "MainActivity: started ")
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