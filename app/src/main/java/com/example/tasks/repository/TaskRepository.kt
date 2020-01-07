package com.example.tasks.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.tasks.constants.DataBaseConstant
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

    fun get(id : Int): TaskEntity? {
        var taskEntity : TaskEntity? = null
        try {
            val cursor : Cursor
            val db = mTaskDataBaseHelper.readableDatabase
            val projection = arrayOf(DataBaseConstant.TASK.COLUMNS.ID,
                DataBaseConstant.TASK.COLUMNS.USERID,
                DataBaseConstant.TASK.COLUMNS.PRIORITYID,
                DataBaseConstant.TASK.COLUMNS.DESCRIPTION,
                DataBaseConstant.TASK.COLUMNS.DUEDATE,
                DataBaseConstant.TASK.COLUMNS.COMPLETE)


            val selection = "${DataBaseConstant.TASK.COLUMNS.ID} = ?"
            val selectionArgs = arrayOf(id.toString())
            cursor = db.query(DataBaseConstant.TASK.TABLE_NAME,projection,selection,selectionArgs,null,null,null)

            if(cursor.count > 0){
                //Pega primeira linha
                cursor.moveToFirst()

                val taskId = cursor.getInt(cursor.getColumnIndex(DataBaseConstant.TASK.COLUMNS.ID))
                val userId = cursor.getInt(cursor.getColumnIndex(DataBaseConstant.TASK.COLUMNS.USERID))
                val priorityId = cursor.getInt(cursor.getColumnIndex(DataBaseConstant.TASK.COLUMNS.PRIORITYID))
                val description = cursor.getString(cursor.getColumnIndex(DataBaseConstant.TASK.COLUMNS.DESCRIPTION))
                val dueDate = cursor.getString(cursor.getColumnIndex(DataBaseConstant.TASK.COLUMNS.DUEDATE))
                val complete = (cursor.getInt(cursor.getColumnIndex(DataBaseConstant.TASK.COLUMNS.COMPLETE)) == 1)
                //Preencho a entidade
                taskEntity = TaskEntity(taskId, userId, priorityId, description, dueDate, complete)
            }

            cursor.close()
        }catch (ex : Exception){
            return taskEntity
        }
        return taskEntity

    }

    fun insert(task : TaskEntity){
        try{
            val db = mTaskDataBaseHelper.writableDatabase
            val complete : Int = if (task.complete) 1 else 0


            val values = ContentValues()
            values.put(DataBaseConstant.TASK.COLUMNS.USERID, task.userId)
            values.put(DataBaseConstant.TASK.COLUMNS.PRIORITYID, task.priorityId)
            values.put(DataBaseConstant.TASK.COLUMNS.DESCRIPTION, task.description)
            values.put(DataBaseConstant.TASK.COLUMNS.DUEDATE, task.dueDate)
            values.put(DataBaseConstant.TASK.COLUMNS.COMPLETE, complete)

            db.insert(DataBaseConstant.TASK.TABLE_NAME, null, values)
        }catch (ex : Exception){
            throw ex
        }


    }

    fun update(task : TaskEntity){

        try{
            val db = mTaskDataBaseHelper.writableDatabase
            val complete : Int = if (task.complete) 1 else 0


            val values = ContentValues()
            values.put(DataBaseConstant.TASK.COLUMNS.USERID, task.userId)
            values.put(DataBaseConstant.TASK.COLUMNS.PRIORITYID, task.priorityId)
            values.put(DataBaseConstant.TASK.COLUMNS.DESCRIPTION, task.description)
            values.put(DataBaseConstant.TASK.COLUMNS.DUEDATE, task.dueDate)
            values.put(DataBaseConstant.TASK.COLUMNS.COMPLETE, complete)

            val selection = "${DataBaseConstant.TASK.COLUMNS.ID} = ?"
            val selectionArgs = arrayOf(task.id.toString())
            db.update(DataBaseConstant.TASK.TABLE_NAME, values,selection, selectionArgs)
        }catch (ex : Exception){
            throw ex
        }

    }

    fun delete(task : TaskEntity){

        try {
            val db = mTaskDataBaseHelper.writableDatabase
            val where = "${DataBaseConstant.TASK.COLUMNS.ID} = ?"
            val whereArgs = arrayOf(task.id.toString())
            db.delete(DataBaseConstant.TASK.TABLE_NAME, where, whereArgs)
        }catch (ex : Exception){
            throw ex
        }
    }

    fun getList(userId: Int, mTaskFilter: Int) : MutableList<TaskEntity>{
        val list = mutableListOf<TaskEntity>()
        try {
            val cursor : Cursor
            val db = mTaskDataBaseHelper.readableDatabase


            cursor = db.rawQuery("SELECT * FROM ${DataBaseConstant.TASK.TABLE_NAME} " +
                    "WHERE ${DataBaseConstant.TASK.COLUMNS.USERID} = $userId " +
                    "AND ${DataBaseConstant.TASK.COLUMNS.COMPLETE} = $mTaskFilter", null)

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