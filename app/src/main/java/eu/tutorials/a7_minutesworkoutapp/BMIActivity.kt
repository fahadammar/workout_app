package eu.tutorials.a7_minutesworkoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import eu.tutorials.a7_minutesworkoutapp.databinding.ActivityBmiBinding

class BMIActivity : AppCompatActivity() {

    private var binding : ActivityBmiBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarBmiActivity)
        // SET BACK BUTTON
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // SETTING A TITLE IN THE ACTION BAR
        supportActionBar?.title = getString(R.string.calculateBMI)

        binding?.toolbarBmiActivity?.setNavigationOnClickListener {
            onBackPressed()
        }

    }
}