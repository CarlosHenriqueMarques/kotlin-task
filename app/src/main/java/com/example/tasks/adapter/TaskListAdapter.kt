package com.example.tasks.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tasks.R
import com.example.tasks.entities.OnTaskListFragmentInteractionListener
import com.example.tasks.entities.TaskEntity
import com.example.tasks.viewholder.TaskViewHolder

class TaskListAdapter(val taskList : List<TaskEntity>, val listener : OnTaskListFragmentInteractionListener) : RecyclerView.Adapter<TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.row_task_list, parent, false)
        return TaskViewHolder(view,listener)
    }

    override fun getItemCount(): Int {
        return taskList.count()
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.bindData(task)
    }
}