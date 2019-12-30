package com.example.tasks.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tasks.R
import kotlinx.android.synthetic.main.fragment_task_list.view.*


class TaskListFragment : Fragment() {
    private lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
       // return inflater.inflate(R.layout.fragment_task_list, container, false)

        val view : View = inflater.inflate(R.layout.fragment_task_list, container, false)
        view.floatAddTask.setOnClickListener {
            val intent = Intent(activity, TaskFormActivity::class.java)
           startActivity(intent)
        }

        return view
        /*
             return inflater.inflate(R.layout.fragment_setup, container, false)

        val view: View = inflater!!.inflate(R.layout.fragment_setup, container, false)

        btnSetup.setOnClickListener { view ->
            Log.d("btnSetup", "Selected")
        }

        // Return the fragment view/layout
        return view


         */
    }

    companion object {
        fun newInstance() : TaskListFragment {
            return TaskListFragment()
        }
    }
}
