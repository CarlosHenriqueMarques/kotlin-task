package com.example.tasks.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.tasks.constants.DataBaseConstant

class TaskDataBaseHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,
    DATABASE_VERSION) {

    companion object {
        private val DATABASE_VERSION = 2
        private val DATABASE_NAME = "task.db"
    }

    private val createTableUser = """
       CREATE TABLE ${DataBaseConstant.USER.TABLE_NAME}(
         ${DataBaseConstant.USER.COLUMNS.ID} INTEGER PRIMARY KEY AUTOINCREMENT,
         ${DataBaseConstant.USER.COLUMNS.NAME} TEXT,
         ${DataBaseConstant.USER.COLUMNS.EMAIL} TEXT,
         ${DataBaseConstant.USER.COLUMNS.PASSWROD} TEXT
    )"""

    private val createTablePriority = """
       CREATE TABLE ${DataBaseConstant.PRIORITY.TABLE_NAME}(
         ${DataBaseConstant.PRIORITY.COLUMNS.ID} INTEGER PRIMARY KEY,
         ${DataBaseConstant.PRIORITY.COLUMNS.DESCRIPTION} TEXT
    )"""

    private val createTableTask = """
       CREATE TABLE ${DataBaseConstant.TASK.TABLE_NAME}(
         ${DataBaseConstant.TASK.COLUMNS.ID} INTEGER PRIMARY KEY AUTOINCREMENT,
         ${DataBaseConstant.TASK.COLUMNS.USERID} INTEGER,
         ${DataBaseConstant.TASK.COLUMNS.PRIORITYID} INTEGER,
         ${DataBaseConstant.TASK.COLUMNS.DESCRIPTION} TEXT,
         ${DataBaseConstant.TASK.COLUMNS.COMPLETE} INTEGER,
         ${DataBaseConstant.TASK.COLUMNS.DUEDATE    } TEXT
    )"""

    private val insertPriorities = """INSERT INTO ${DataBaseConstant.PRIORITY.TABLE_NAME} 
         VALUES (1,'Baixa'),(2,'Média'),(3,'Alta'),(4,'Crítica')"""

    private val deleteDataBaseUser = "drop table if exists ${DataBaseConstant.USER.TABLE_NAME}"
    private val deleteDataBasePriority = "drop table if exists ${DataBaseConstant.PRIORITY.TABLE_NAME}"
    private val deleteDataBaseTask = "drop table if exists ${DataBaseConstant.TASK.TABLE_NAME}"

    override fun onCreate(sqlLite: SQLiteDatabase?) {
        sqlLite?.execSQL(createTableUser)
        sqlLite?.execSQL(createTablePriority)
        sqlLite?.execSQL(createTableTask)
        sqlLite?.execSQL(insertPriorities)
    }

    override fun onUpgrade(sqlLite: SQLiteDatabase?, p1: Int, p2: Int) {
        //Remoção
        sqlLite?.execSQL(deleteDataBaseUser)
        sqlLite?.execSQL(deleteDataBasePriority)
        sqlLite?.execSQL(deleteDataBaseTask)

        //Criação
        sqlLite?.execSQL(createTableUser)
        sqlLite?.execSQL(createTablePriority)
        sqlLite?.execSQL(createTableTask)
    }
}