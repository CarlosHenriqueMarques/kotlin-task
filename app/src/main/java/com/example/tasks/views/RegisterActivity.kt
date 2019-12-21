package com.example.tasks.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tasks.R
import com.example.tasks.business.UserBusiness
import com.example.tasks.util.ValidationException
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var mUserBussines : UserBusiness

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mUserBussines = UserBusiness(this)
        buttonSave.setOnClickListener {
            handleSave()
        }
    }

    private fun handleSave() {
        try{
            val name = editName.text.toString()
            val email = editEmail.text.toString()
            val password = editPassword.text.toString()
            mUserBussines.insert(name,email,password)
        }catch (ex : ValidationException){
            Toast.makeText(this,ex.message,Toast.LENGTH_LONG).show()
        }catch (ex: Exception){
            Toast.makeText(this,getString(R.string.erro_inesperado),Toast.LENGTH_LONG).show()
        }
    }
}
