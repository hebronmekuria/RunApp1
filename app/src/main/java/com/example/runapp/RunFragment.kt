package com.example.runapp


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

private const val TAG = "RunFragment"

class RunFragment : Fragment() {
    private val activities = mutableListOf<Run>()
    private lateinit var runRecyclerView: RecyclerView
    private lateinit var runAdapter: RunAdapter


    override fun onViewCreated(view:View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch(IO) {
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
                }
            }
        }
        runAdapter.notifyDataSetChanged()
        //newInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_run, container, false)

        val layoutManager = LinearLayoutManager(context)
        runRecyclerView = view.findViewById(R.id.run_recycler_view)
        runRecyclerView.layoutManager = layoutManager
        runRecyclerView.setHasFixedSize(true)
        runAdapter = RunAdapter(view.context, activities)
        runRecyclerView.adapter = runAdapter

        return view
    }


    companion object {
        fun newInstance(): RunFragment {
            return RunFragment()
        }
    }
}