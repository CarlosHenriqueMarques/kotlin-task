package com.example.tasks.business

import android.content.Context
import com.example.tasks.R
import com.example.tasks.constants.TaskConstants
import com.example.tasks.entities.UserEntity
import com.example.tasks.repository.TaskDataBaseHelper
import com.example.tasks.repository.UserRepository
import com.example.tasks.util.SecurityPreferences
import com.example.tasks.util.ValidationException
import java.lang.Exception

class UserBusiness (val context: Context) {

    private val mUserRepository : UserRepository = UserRepository.getInstance(context)
    private val mSecurityPreferences : SecurityPreferences = SecurityPreferences(context)
    fun insert(name : String, email : String, password : String){
        try {

            if(name == "" || email == "" || password == ""){
                throw ValidationException(context.getString(R.string.informe_todos_camspo))
            }
            if(mUserRepository.isEmailExistent(email)){
                throw ValidationException(context.getString(R.string.email_em_uso))
            }
            val userid = mUserRepository.insert(name,email,password)
            mSecurityPreferences.storeString(TaskConstants.KEY.USER_ID,userid.toString())
            mSecurityPreferences.storeString(TaskConstants.KEY.USER_NAME,name)
            mSecurityPreferences.storeString(TaskConstants.KEY.USER_EMAIL,email)
        }catch (ex : Exception){
            throw ex
        }
    }

    fun login(email : String, password : String){
        val user : UserEntity? = mUserRepository.get(email,password)
        if(user != null){
            mSecurityPreferences.storeString(TaskConstants.KEY.USER_ID,user.id.toString())
            mSecurityPreferences.storeString(TaskConstants.KEY.USER_NAME,user.name)
            mSecurityPreferences.storeString(TaskConstants.KEY.USER_EMAIL,user.email)
        }
    }


}
