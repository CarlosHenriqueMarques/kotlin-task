package com.example.tasks.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tasks.R
import com.example.tasks.adapter.TaskListAdapter
import kotlinx.android.synthetic.main.fragment_task_list.view.*


class TaskListFragment : Fragment() {
    private lateinit var mContext: Context
    //private lateinit var mRecyclerTaskList : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        val view : View = inflater.inflate(R.layout.fragment_task_list, container, false)
        mContext = view.context
        view.floatAddTask.setOnClickListener {
            val intent = Intent(activity, TaskFormActivity::class.java)
           startActivity(intent)
        }

        view.recycleTask.adapter = TaskListAdapter()
       // view.recycleTask.layoutManager = LinearLayoutManager(mContext)

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
