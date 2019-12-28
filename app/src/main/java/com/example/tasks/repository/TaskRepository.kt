package com.example.tasks.repository

import android.content.Context
import android.database.Cursor
import com.example.tasks.constants.DataBaseConstant
import com.example.tasks.entities.PriorityEntity
import com.example.tasks.entities.TaskEntity
import java.lang.Exception

class TaskRepository private constructor(context: Context){

    private var mTaskDataBaseHelper : TaskDataBaseHelper = TaskDataBaseHelper(context)

    companion object{
        fun getInstance (context: Context): TaskRepository {
            if (INSTANCE == null){
                INSTANCE = TaskRepository(context)
            }
            return INSTANCE as TaskRepository
        }
        private var INSTANCE : TaskRepository? = null
    }

    fun getList(userId : Int) : MutableList<TaskEntity>{
        val list = mutableListOf<TaskEntity>()
        try {
            val cursor : Cursor
            val db = mTaskDataBaseHelper.readableDatabase


            cursor = db.rawQuery("SELECT * FROM ${DataBaseConstant.TASK.TABLE_NAME} " +
                    "WHERE ${DataBaseConstant.TASK.COLUMNS.USERID} = $userId", null)

            if(cursor.count > 0){
                while (cursor.moveToNext()){
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstant.TASK.COLUMNS.ID))
                    val priorityId = cursor.getInt(cursor.getColumnIndex(DataBaseConstant.TASK.COLUMNS.PRIORITYID))
                    val description = cursor.getString(cursor.getColumnIndex(DataBaseConstant.TASK.COLUMNS.DESCRIPTION))
                    val dueDate = cursor.getString(cursor.getColumnIndex(DataBaseConstant.TASK.COLUMNS.DUEDATE))
                    val complete = (cursor.getInt(cursor.getColumnIndex(DataBaseConstant.TASK.COLUMNS.COMPLETE)) == 1)

                    list.add(TaskEntity(id, userId, priorityId, description, dueDate, complete))
                }
            }

            cursor.close()

        }catch (ex : Exception){
            return list
        }
        return list
    }
}