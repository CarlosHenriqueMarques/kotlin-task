package com.example.tasks.viewholder

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.service.voice.VoiceInteractionSession
import android.view.View

import androidx.recyclerview.widget.RecyclerView
import com.example.tasks.R
import com.example.tasks.entities.OnTaskListFragmentInteractionListener

import com.example.tasks.entities.TaskEntity
import com.example.tasks.repository.PriorityCacheConstants

import kotlinx.android.synthetic.main.row_task_list.view.*

class TaskViewHolder(itemView : View,val context: Context, val listener : OnTaskListFragmentInteractionListener) : RecyclerView.ViewHolder(itemView) {

    fun bindData(task : TaskEntity){
        itemView.textDescription.text = task.description
        itemView.textPriority.text = PriorityCacheConstants.getPriorityDescription(task.priorityId)
        itemView.textDueDate.text = task.dueDate

        if(task.complete){
            itemView.imageTask.setImageResource(R.drawable.ic_done)
        }else{
            itemView.imageTask.setImageResource(R.drawable.ic_todo)
        }

        //Evento de clique para edição
        itemView.textDescription.setOnClickListener {
            listener.onClick(task.id)
        }

        itemView.textDescription.setOnLongClickListener {
            showConfirmationDialog(task)

            true
        }

        itemView.imageTask.setOnClickListener {
            if(task.complete){
                listener.onUncompleteClick(task.id)
            }else{
                listener.onCompleteClick(task.id)
            }
        }
    }

    private fun showConfirmationDialog(task: TaskEntity) {
        //listener.onDeleteClick(task.id)
        AlertDialog.Builder(context)
            .setTitle(context.getString(R.string.title_delete_task))
            .setMessage("Deseja realmente remover a tarefa ${task.description} ?")
            .setIcon(R.drawable.ic_delete_task)
            .setPositiveButton("Remover") { dialog, i -> listener.onDeleteClick(task.id)  }
            //.setPositiveButton("Remover",handleRemoval(listener,task.id))
            .setNegativeButton("Cancelar",null).show()
    }

}
/*
Outra forma de fazer o evento de deletar

private class handleRemoval(val listener: OnTaskListFragmentInteractionListener, val taskId: Int) : DialogInterface.OnClickListener{
    override fun onClick(p0: DialogInterface?, p1: Int) {
        listener.onDeleteClick(taskId)
}
 */