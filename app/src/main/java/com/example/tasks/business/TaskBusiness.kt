package com.example.tasks.business

import android.content.Context
import com.example.tasks.constants.TaskConstants
import com.example.tasks.entities.TaskEntity
import com.example.tasks.repository.TaskRepository
import com.example.tasks.util.SecurityPreferences

class TaskBusiness(context: Context) {
    private val mTaskRepository : TaskRepository = TaskRepository.getInstance(context)
    private lateinit var mSecurityPreferences : SecurityPreferences
    fun getList() : MutableList<TaskEntity>{

        val userId = mSecurityPreferences.getStoreString(TaskConstants.KEY.USER_ID)?.toInt()

        if(userId != null){
            return  mTaskRepository.getList(userId)
        }else{
            return mutableListOf()
        }

    }

    fun insert(taskEntity: TaskEntity){
        return mTaskRepository.insert(taskEntity)
    }
}