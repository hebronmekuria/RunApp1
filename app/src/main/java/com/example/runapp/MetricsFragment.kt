package com.example.runapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import java.text.DecimalFormat

class MetricsFragment : Fragment() {
    private val activities = mutableListOf<Run>()
    private lateinit var expTextView: TextView
    private lateinit var disTextView: TextView
    private lateinit var durTextView: TextView

    override fun onViewCreated(view:View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        expTextView = view.findViewById(R.id.experience_avg)
        disTextView = view.findViewById(R.id.distance_avg)
        durTextView = view.findViewById(R.id.duration_avg)

        lifecycleScope.launch {
            (activity?.application as RunApplication).db.runDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    Run(
                        entity.activity,
                        entity.distance,
                        entity.duration,
                        entity.experience
                    )
                }.also { mappedList ->
                    activities.clear()
                    activities.addAll(mappedList)
                    val triple = calculate_metrics(activities)
                    expTextView.text = "Average experience: " + triple.first + "/10"
                    disTextView.text = "Average distance: " + triple.second + " miles"
                    durTextView.text = "Average duration: " + triple.third + " min"
                }
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_metrics, container, false)
    }

    companion object {
        fun newInstance(): MetricsFragment{
            return MetricsFragment()
        }
    }

    fun calculate_metrics(run_act:List<Run>):Triple<String,String,String>{
        val dec = DecimalFormat("#.0")
        val count = run_act.size
        var exp =0.0
        var distance = 0.0
        var duration = 0.0
        for (activity in run_act){
            exp += activity.experience
            distance += activity.distance
            duration += activity.duration
        }
        return Triple(dec.format(exp/count), dec.format(distance/count), dec.format(duration/count))
    }
}