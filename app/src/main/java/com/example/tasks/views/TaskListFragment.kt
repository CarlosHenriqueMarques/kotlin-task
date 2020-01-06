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
import com.example.tasks.business.TaskBusiness
import com.example.tasks.constants.TaskConstants
import com.example.tasks.entities.TaskEntity
import com.example.tasks.util.SecurityPreferences
import kotlinx.android.synthetic.main.fragment_task_list.view.*


class TaskListFragment : Fragment() {
    private lateinit var mContext: Context
    private lateinit var mRecyclerTaskList : RecyclerView
    private lateinit var mTaskBussines : TaskBusiness
    private lateinit var mSecurityPreferences : SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        val view : View = inflater.inflate(R.layout.fragment_task_list, container, false)
        mContext = view.context
        mTaskBussines = TaskBusiness(mContext)
        mSecurityPreferences = SecurityPreferences(mContext)

        view.floatAddTask.setOnClickListener {
            val intent = Intent(activity, TaskFormActivity::class.java)
           startActivity(intent)
        }

        mRecyclerTaskList = view.findViewById(R.id.recycleTask)
        mRecyclerTaskList.adapter = TaskListAdapter(mutableListOf())
        view.recycleTask.layoutManager = LinearLayoutManager(mContext)


        //view.recycleTask.layoutManager = LinearLayoutManager(mContext)

        return view
    }

    override fun onResume() {
        super.onResume()
        loadTask()
    }

    private fun loadTask() {
        mRecyclerTaskList.adapter = TaskListAdapter(mTaskBussines.getList())
    }

    companion object {
        fun newInstance() : TaskListFragment {
            return TaskListFragment()
        }
    }
}
