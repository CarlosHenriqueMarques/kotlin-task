package com.example.tasks.business

import android.content.Context
import com.example.tasks.R
import com.example.tasks.repository.TaskDataBaseHelper
import com.example.tasks.repository.UserRepository
import com.example.tasks.util.ValidationException
import java.lang.Exception

class UserBusiness (val context: Context) {

    private val mUserRepository : UserRepository = UserRepository.getInstance(context)

    fun insert(name : String, email : String, password : String){

        try {

            if(name == "" || email == "" || password == ""){
                throw ValidationException(context.getString(R.string.informe_todos_camspo))
            }
            if(mUserRepository.isEmailExistent(email)){
                throw ValidationException(context.getString(R.string.email_em_uso))
            }
            val userid = mUserRepository.insert(name,email,password)
        }catch (ex : Exception){
            throw ex
        }
    }

}
