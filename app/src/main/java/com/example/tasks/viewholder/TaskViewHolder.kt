package com.example.tasks.viewholder

import android.view.View

import androidx.recyclerview.widget.RecyclerView
import com.example.tasks.R

import com.example.tasks.entities.TaskEntity
import com.example.tasks.repository.PriorityCacheConstants
import kotlinx.android.synthetic.main.activity_task_form.view.*
import kotlinx.android.synthetic.main.row_task_list.view.*

class TaskViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(task : TaskEntity){
        itemView.textDescription.text = task.description
        itemView.textPriority.text = PriorityCacheConstants.getPriorityDescription(task.priorityId)
        itemView.textDueDate.text = task.dueDate

        if(task.complete){
            itemView.imageTask.setImageResource(R.drawable.ic_done)
        }else{
            itemView.imageTask.setImageResource(R.drawable.ic_todo)
        }

    }

}