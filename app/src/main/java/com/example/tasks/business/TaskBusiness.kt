package com.example.tasks.business

import android.content.Context
import com.example.tasks.constants.TaskConstants
import com.example.tasks.entities.TaskEntity
import com.example.tasks.repository.TaskRepository
import com.example.tasks.util.SecurityPreferences

class TaskBusiness(context: Context) {
    private val mTaskRepository : TaskRepository = TaskRepository.getInstance(context)
    private var mSecurityPreferences: SecurityPreferences = SecurityPreferences(context)

    fun getList(mTaskFilter: Int): MutableList<TaskEntity>{

        val userId = mSecurityPreferences.getStoreString(TaskConstants.KEY.USER_ID).toInt()
        return  mTaskRepository.getList(userId, mTaskFilter)

        /*if(userId != null){
            return  mTaskRepository.getList(userId)
        }else{
            return mutableListOf()
        }*/

    }

    fun insert(taskEntity: TaskEntity){
        return mTaskRepository.insert(taskEntity)
    }

    fun update(taskEntity: TaskEntity){
        return mTaskRepository.update(taskEntity)
    }

    fun get(id : Int) = mTaskRepository.get(id)

    fun delete(id : Int) = mTaskRepository.delete(id)

    fun complete(id: Int, complete : Boolean) {
        val task = mTaskRepository.get(id)
        if(task != null){
            task.complete = complete
            mTaskRepository.update(task)
        }
    }
}