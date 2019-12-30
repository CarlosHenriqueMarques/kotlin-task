package com.example.tasks.business

import android.content.Context
import com.example.tasks.entities.TaskEntity
import com.example.tasks.repository.TaskRepository

class TaskBusiness(context: Context) {
    private val mTaskRepository : TaskRepository = TaskRepository.getInstance(context)

    fun getList(userId : Int) : MutableList<TaskEntity>{
        return  mTaskRepository.getList(userId)
    }

    fun insert(taskEntity: TaskEntity){
        return mTaskRepository.insert(taskEntity)
    }
}