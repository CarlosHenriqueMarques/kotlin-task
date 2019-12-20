package com.example.tasks.business

import android.content.Context
import com.example.tasks.repository.TaskDataBaseHelper
import com.example.tasks.repository.UserRepository

class UserBusiness (val context: Context) {

    private val mUserRepository : UserRepository = UserRepository.getInstance(context)

    fun insert(name : String, email : String, password : String){
        val userid = mUserRepository.insert(name,email,password)
        val debug = ""
    }

}
