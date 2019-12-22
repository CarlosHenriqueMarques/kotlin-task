package com.example.tasks.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tasks.R
import com.example.tasks.business.UserBusiness
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var mUserBussines : UserBusiness

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mUserBussines = UserBusiness(this)

        buttonLogin.setOnClickListener {
            handleLogin()
        }
    }

    private fun handleLogin() {
        val email = editEmail.text.toString()
        val password = editPassword.text.toString()

        if(mUserBussines.login(email,password)){
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }else{
            Toast.makeText(this,getString(R.string.user_password_incorret), Toast.LENGTH_LONG).show()
        }
    }
}
