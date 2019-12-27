package com.example.tasks.constants

class DataBaseConstant {
    object USER {
        val TABLE_NAME = "user"
        object COLUMNS{
            val ID = "id"
            val NAME = "name"
            val EMAIL = "email"
            val PASSWROD = "password"

        }
    }

    object PRIORITY {
        val TABLE_NAME = "priority"
        object COLUMNS{
            val ID = "id"
            val DESCRIPTION = "description"

        }
    }

    object TASK {
        val TABLE_NAME = "task"
        object COLUMNS{
            val ID = "id"
            val USERID = "userid"
            val PRIORITYID = "priorityid"
            val DESCRIPTION = "description"
            val COMPLETE = "complete"
            val DUEDATE = "duedate"

        }
    }
}