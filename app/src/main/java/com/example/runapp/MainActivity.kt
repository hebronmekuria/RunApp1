package com.example.runapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import com.example.runapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val activities = mutableListOf<Run>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var addButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // var new_activity = Run("hi",5.5.toFloat(),5.5.toFloat(),5)

        var editActivityResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            // If the user comes back to this activity from EditActivity
            // with no error or cancellation
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                // Get the data passed from EditActivity
                if (data != null) {
                    val new_activity = data?.getSerializableExtra("Results") as Run
                    lifecycleScope.launch(Dispatchers.IO) {
                        (application as RunApplication).db.runDao().insert(
                            RunEntity(new_activity.activity,new_activity.distance,new_activity.duration, new_activity.experience)
                        )
                    }

                }
            }
        }


        addButton = findViewById(R.id.button)

        val runFragment: Fragment = RunFragment()
        val metricsFragment: Fragment = MetricsFragment()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        addButton.setOnClickListener{
            val intent = Intent(this, EnterRun::class.java)
            //startActivity(intent)
            editActivityResultLauncher.launch(intent)
           // bottomNavigationView.selectedItemId = R.id.run

        }

        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.run -> fragment = runFragment
                R.id.metrics -> fragment = metricsFragment
            }
            replaceFragment(fragment)
            true
        }

        // Set default selection
        bottomNavigationView.selectedItemId = R.id.run

    }

    private fun replaceFragment(activityFragment: Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.activity_frame_layout, activityFragment)
        fragmentTransaction.commit()
    }

}