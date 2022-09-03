package eu.tutorials.a7_minutesworkoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import eu.tutorials.a7_minutesworkoutapp.databinding.ActivityExcerciseBinding

class ExcerciseActivity : AppCompatActivity() {

    lateinit var excerciseBinding: ActivityExcerciseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        excerciseBinding = ActivityExcerciseBinding.inflate(layoutInflater)
        val view = excerciseBinding.root
        setContentView(view)

        setSupportActionBar(excerciseBinding.toolbar)

        // we can use this variable or we can use directly.
        val actionBar = supportActionBar // getSupportActionBar()

        if(supportActionBar != null){
            // Show back button using actionBar.setDisplayHomeAsUpEnabled(true) this will enable the back button.
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        // In order to add the back button
        excerciseBinding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}