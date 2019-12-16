package com.example.tasks.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.tasks.constants.DataBaseConstant

class TaskDataBaseHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,
    DATABASE_VERSION) {

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "task.db"
    }

    private val createTableUser = """
       CREATE TABLE ${DataBaseConstant.USER.TABLE_NAME}(
         ${DataBaseConstant.USER.COLUMNS.ID} INTEGER PRIMARY KEY AUTOINCREMENT,
         ${DataBaseConstant.USER.COLUMNS.NAME} TEXT
         ${DataBaseConstant.USER.COLUMNS.EMAIL} TEXT
         ${DataBaseConstant.USER.COLUMNS.PASSWROD} TEXT
    )"""

    private val deleteDataBaseUser = "drop table if exists ${DataBaseConstant.USER.TABLE_NAME}"

    override fun onCreate(sqlLite: SQLiteDatabase?) {
        sqlLite?.execSQL(createTableUser)
    }

    override fun onUpgrade(sqlLite: SQLiteDatabase?, p1: Int, p2: Int) {
        sqlLite?.execSQL(deleteDataBaseUser)
        sqlLite?.execSQL(createTableUser)
    }
}