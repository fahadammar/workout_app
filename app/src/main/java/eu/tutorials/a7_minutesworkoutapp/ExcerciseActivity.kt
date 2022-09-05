package eu.tutorials.a7_minutesworkoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import eu.tutorials.a7_minutesworkoutapp.databinding.ActivityExcerciseBinding
import java.util.*

class ExcerciseActivity : AppCompatActivity() {

    // BINDING VARIABLE
    var excerciseBinding: ActivityExcerciseBinding? = null

    // COUNT DOWN VARIABLES
    private var timer : CountDownTimer? = null
    private var progressNum = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        excerciseBinding = ActivityExcerciseBinding.inflate(layoutInflater)
        val view = excerciseBinding!!.root
        setContentView(view)

        setSupportActionBar(excerciseBinding!!.toolbar)

        // we can use this variable or we can use directly.
        val actionBar = supportActionBar // getSupportActionBar()

        if(supportActionBar != null){
            // Show back button using actionBar.setDisplayHomeAsUpEnabled(true) this will enable the back button.
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        // In order to add the back button
        excerciseBinding!!.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        setupProgressView()
    }

    private fun setupProgressView(){
        /*
         * Here firstly we will check if the timer is running the and it is not null then cancel the running timer and start the new one.
         * And set the progress to initial which is 0.
         */
        if(timer != null)
        {
            timer!!.cancel()
            progressNum = 0
        }
        // This function is which contains the count flow. It's used to set the progress details
        theProgressBarFunctionality()
    }

    // THE COUNT DOWN TIMER FUNCTION
    private fun theProgressBarFunctionality(){
        excerciseBinding!!.progressBar.progress = progressNum
        // Here we have started a timer of 10 seconds so the 10000 is milliseconds is 10 seconds and the countdown interval is 1 second so it 1000.
        timer = object : CountDownTimer(1000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                // Increasing by 1
                progressNum++
                // Indicates progress bar progress
                excerciseBinding!!.progressBar.progress = 10 - progressNum
                // The TextView Number in Between the Progress. It's basically the text in terms of seconds
                excerciseBinding!!.tvTimer.text = (10 - progressNum).toString()
            }
            override fun onFinish() {
                // When the 10 seconds will complete this will be executed.
                Toast.makeText(
                    this@ExcerciseActivity,
                    R.string.countFinishMsg,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        excerciseBinding = null
        if(timer != null)
        {
            timer!!.cancel()
            progressNum = 0
        }
    }
}

/**
 * COUNT DOWN TIMER - PARAMETERS
 * @param millisInFuture -> The number of millis in the future from the call
 *   to {#start()} until the countdown is done and {#onFinish()}
 *   is called.
 * @param countDownInterval -> The interval along the way to receive
 *   {#onTick(long)} callbacks.
 */