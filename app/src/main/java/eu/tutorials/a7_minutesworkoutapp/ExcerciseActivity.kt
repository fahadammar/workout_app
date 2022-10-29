package eu.tutorials.a7_minutesworkoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Toast
import eu.tutorials.a7_minutesworkoutapp.Constants.Constants
import eu.tutorials.a7_minutesworkoutapp.Model.ExcerciseModel
import eu.tutorials.a7_minutesworkoutapp.databinding.ActivityExcerciseBinding
import java.util.*

class ExcerciseActivity : AppCompatActivity() {

    val TAG = "excerciseActivityTAG"

    // BINDING VARIABLE
    var binding: ActivityExcerciseBinding? = null

    // COUNT DOWN VARIABLES
    private var restTimer : CountDownTimer? = null
    private var restProgress = 0

    // Excercise Timers
    private var excerciseTimer : CountDownTimer? = null
    private var excerciseProgress = 0

    private var exerciseTimerDuration:Long = 30

    //  The Variable for the exercise list and current position of exercise here it is
    //  -1 as the list starting element is 0.)
    private var excerciseList: ArrayList<ExcerciseModel>? = null // We will initialize the list later.
    private var currentExercisePosition = -1 // Current Position of Exercise.
    
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

        excerciseList = Constants.defaultExerciseList()

        setupRestView()
    }

    private fun setupRestView(){
        // We are making the first timer visible
        binding?.flRestView?.visibility = View.VISIBLE
        binding?.tvTitle?.visibility = View.VISIBLE
        binding?.tvUpcomingExerciseName?.visibility = View.VISIBLE
        binding?.upcomingLabel?.visibility = View.VISIBLE
        // We are making the below stuff invisible - it will appear after the first timer completes
        binding?.tvExerciseName?.visibility = View.INVISIBLE
        binding?.flExerciseView?.visibility = View.INVISIBLE
        binding?.ivImage?.visibility = View.INVISIBLE

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

        // Here we will get the next excercise position
        binding?.tvUpcomingExerciseName?.text =excerciseList!![currentExercisePosition + 1].getName()

        // This function is which contains the count flow. It's used to set the progress details
        setRestProgressBar()
    }

    /**
     * Function is used to set the progress of timer using the progress
     */
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
                // Increasing the current position of the exercise after rest view.
                currentExercisePosition++
                // HERE BELOW IS THE EXCERCISE VIEW - NOW THE EXCERCISE STUFF STARTS
                setupExcerciseView()
            }
        }.start()
    }

    private fun setupExcerciseView(){
        // making the first timer invisible after it completes it time
        binding?.flRestView?.visibility = View.INVISIBLE
        binding?.tvTitle?.visibility = View.INVISIBLE
        binding?.tvUpcomingExerciseName?.visibility = View.INVISIBLE
        binding?.upcomingLabel?.visibility = View.INVISIBLE
        // making the second timer and image's visible
        binding?.tvExerciseName?.visibility = View.VISIBLE
        binding?.flExerciseView?.visibility = View.VISIBLE
        binding?.ivImage?.visibility = View.VISIBLE


        if(excerciseTimer != null){
            excerciseTimer?.cancel()
            excerciseProgress = 0
        }

        // Setting up the current exercise name and image to view to the UI element.
        // START
        /**
         * Here current exercise name and image is set to exercise view.
         */
        binding?.ivImage?.setImageResource(excerciseList!![currentExercisePosition].getImage())
        binding?.tvExerciseName?.text = excerciseList!![currentExercisePosition].getName()

        setExcerciseProgressBar()
    }

    // THE COUNT DOWN TIMER FUNCTION - this is named as -->
    private fun setExcerciseProgressBar(){
        binding!!.progressBar.progress = excerciseProgress
        // Here we have started a timer of 10 seconds so the 10000 is milliseconds is 10 seconds and the countdown interval is 1 second so it 1000.
        restTimer = object : CountDownTimer(exerciseTimerDuration * 1000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                // Increasing by 1
                excerciseProgress++
                // Indicates progress bar progress
                binding!!.progressBarExcercise.progress = exerciseTimerDuration.toInt() - excerciseProgress
                // The TextView Number in Between the Progress. It's basically the text in terms of seconds
                binding!!.tvTimerExcercise.text = (exerciseTimerDuration.toInt() - excerciseProgress).toString()
            }
            override fun onFinish() {
                // Updating the view after completing the 30 seconds exercise.
                if(currentExercisePosition < excerciseList?.size!! - 1) {
                    logFunction()
                    // After the timer finishes, then user is taken back to the first timer
                    setupRestView()
                }
                else {
                    Toast.makeText(
                        this@ExcerciseActivity,
                        R.string.congratsMSG,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }.start()
    }

    private fun logFunction() {
        Log.i(TAG, "logFunction: *** In SetExcerciseProgressBar ***")
        Log.i(TAG, "logFunction: *** currentExercisePosition = $currentExercisePosition ***")
        Log.i(TAG, "logFunction: *** excerciseList-size = ${excerciseList?.size!!} ***")
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