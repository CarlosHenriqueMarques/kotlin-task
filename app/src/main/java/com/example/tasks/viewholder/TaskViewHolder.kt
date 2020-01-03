package com.example.tasks.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tasks.R
import com.example.tasks.entities.TaskEntity

class TaskViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    private val mTaskDescription : TextView = itemView.findViewById(R.id.textDescription)
    fun bindData(task : TaskEntity){
        mTaskDescription.text = task.description
    }

}