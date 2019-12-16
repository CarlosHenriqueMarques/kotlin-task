package com.example.tasks.repository

import android.content.ContentValues
import android.content.Context
import com.example.tasks.constants.DataBaseConstant

class UserRepository private constructor(context : Context){
    private var mTaskDataBaseHelper : TaskDataBaseHelper = TaskDataBaseHelper(context)

    companion object{
        fun getInstance (context: Context): UserRepository {
            if (INSTANCE == null){
                INSTANCE = UserRepository(context)
            }
            return INSTANCE as UserRepository
        }
        private var INSTANCE : UserRepository? = null
    }

    fun insert(name: String, email : String, password: String) : Int{
        val db = mTaskDataBaseHelper.writableDatabase
        val values = ContentValues()

        values.put(DataBaseConstant.USER.COLUMNS.NAME, name)
        values.put(DataBaseConstant.USER.COLUMNS.EMAIL, email)
        values.put(DataBaseConstant.USER.COLUMNS.PASSWROD, password)

        return db.insert(DataBaseConstant.USER.TABLE_NAME,null,values).toInt()

    }

}