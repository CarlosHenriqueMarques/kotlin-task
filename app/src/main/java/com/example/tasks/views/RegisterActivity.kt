package com.example.tasks.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tasks.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        buttonSave.setOnClickListener {
            handleSave()
        }
    }

    private fun handleSave() {

    }
}
