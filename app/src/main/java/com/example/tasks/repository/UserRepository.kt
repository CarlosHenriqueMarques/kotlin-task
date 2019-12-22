package com.example.tasks.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
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

    fun isEmailExistent(email: String): Boolean {
        var ret = false
        try {
            val cursor : Cursor
            val db = mTaskDataBaseHelper.readableDatabase
            val projection = arrayOf(DataBaseConstant.USER.COLUMNS.ID)

            val selection = "${DataBaseConstant.USER.COLUMNS.EMAIL} = ?"
            val selectionArgs = arrayOf(email)
            cursor = db.query(DataBaseConstant.USER.TABLE_NAME,projection,selection,selectionArgs,null,null,null)
            ret = cursor.count > 0
            cursor.close()
        }catch (ex : Exception){
            throw ex
        }
        return ret

        //db.rawQuery("select * from user where email == gabriel", null)


    }

}