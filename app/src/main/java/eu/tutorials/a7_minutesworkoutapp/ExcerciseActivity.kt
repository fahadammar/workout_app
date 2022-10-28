package eu.tutorials.a7_minutesworkoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import eu.tutorials.a7_minutesworkoutapp.databinding.ActivityExcerciseBinding
import java.util.*

class ExcerciseActivity : AppCompatActivity() {

    // BINDING VARIABLE
    var binding: ActivityExcerciseBinding? = null

    // COUNT DOWN VARIABLES
    private var restTimer : CountDownTimer? = null
    private var restProgress = 0

    // Excercise Timers
    private var excerciseTimer : CountDownTimer? = null
    private var excerciseProgress = 0
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // BINDING
        binding = ActivityExcerciseBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)

        setSupportActionBar(binding?.toolbarExercise)

        // we can use this variable or we can use directly.
        val actionBar = supportActionBar // getSupportActionBar()

        if(supportActionBar != null){
            // Show back button using actionBar.setDisplayHomeAsUpEnabled(true) this will enable the back button.
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        // In order to add the back button
        binding?.toolbarExercise?.setNavigationOnClickListener {
            onBackPressed()
        }

        setupRestView()
    }

    private fun setupRestView(){
        /*
         * Here firstly we will check if the timer is running the
         * and it is not null then cancel the running timer and start the new one.
         * And set the progress to initial which is 0.
         */
        if(restTimer != null)
        {
            restTimer!!.cancel()
            restProgress = 0
        }
        // This function is which contains the count flow. It's used to set the progress details
        setRestProgressBar()
    }

    // THE COUNT DOWN TIMER FUNCTION - this is named as -->
    private fun setRestProgressBar(){
        binding?.progressBar?.progress = restProgress
        // Here we have started a timer of 10 seconds so the 10000 is milliseconds is 10 seconds and the countdown interval is 1 second so it 1000.
        restTimer = object : CountDownTimer(10000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                // Increasing by 1
                restProgress++
                // Indicates progress bar progress
                binding?.progressBar?.progress = 10 - restProgress
                // The TextView Number in Between the Progress. It's basically the text in terms of seconds
                binding?.tvTimer?.text = (10 - restProgress).toString()
            }
            override fun onFinish() {
                // HERE BELOW IS THE EXCERCISE VIEW - NOW THE EXCERCISE STUFF STARTS
                setupExcerciseView()
                // When the 10 seconds will complete this will be executed.
                Toast.makeText(
                    this@ExcerciseActivity,
                    R.string.countFinishMsg,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }.start()
    }

    private fun setupExcerciseView(){
        binding?.flProgressBar?.visibility = View.INVISIBLE
        binding?.tvTitle?.text = "Excercise Name"
        binding?.flExcercise?.visibility = View.VISIBLE

        if(excerciseTimer != null){
            excerciseTimer?.cancel()
            excerciseProgress = 0
        }

        setExcerciseProgressBar()
    }

    // THE COUNT DOWN TIMER FUNCTION - this is named as -->
    private fun setExcerciseProgressBar(){
        binding!!.progressBar.progress = restProgress
        // Here we have started a timer of 10 seconds so the 10000 is milliseconds is 10 seconds and the countdown interval is 1 second so it 1000.
        restTimer = object : CountDownTimer(30000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                // Increasing by 1
                excerciseProgress++
                // Indicates progress bar progress
                binding!!.progressBarExcercise.progress = 30 - excerciseProgress
                // The TextView Number in Between the Progress. It's basically the text in terms of seconds
                binding!!.tvTimerExcercise.text = (30 - excerciseProgress).toString()
            }
            override fun onFinish() {
                setupExcerciseView()
            }
        }.start()
    }

    override fun onDestroy() {
        if(restTimer != null)
        {
            restTimer?.cancel()
            restProgress = 0
        }

        super.onDestroy()
        binding = null

        if(excerciseTimer != null)
        {
            excerciseTimer?.cancel()
            excerciseProgress = 0
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