package com.example.tasks.repository

import android.content.Context
import android.database.Cursor
import com.example.tasks.constants.DataBaseConstant
import com.example.tasks.entities.PriorityEntity
import java.lang.Exception

class PriorityRepository private constructor(context: Context) {
    private var mTaskDataBaseHelper : TaskDataBaseHelper = TaskDataBaseHelper(context)
    companion object{
        fun getInstance (context: Context): PriorityRepository {
            if (INSTANCE == null){
                INSTANCE = PriorityRepository(context)
            }
            return INSTANCE as PriorityRepository
        }
        private var INSTANCE : PriorityRepository? = null
    }

    fun getList() : MutableList<PriorityEntity>{
        val list = mutableListOf<PriorityEntity>()
        try {
            val cursor : Cursor
            val db = mTaskDataBaseHelper.readableDatabase


            cursor = db.rawQuery("SELECT * FROM ${DataBaseConstant.PRIORITY.TABLE_NAME}", null)

            if(cursor.count > 0){
                while (cursor.moveToNext()){
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstant.PRIORITY.COLUMNS.ID))
                    val description = cursor.getString(cursor.getColumnIndex(DataBaseConstant.PRIORITY.COLUMNS.DESCRIPTION))
                    list.add(PriorityEntity(id,description))
                }
            }

            cursor.close()

        }catch (ex : Exception){
            return list
        }
        return list
    }
}