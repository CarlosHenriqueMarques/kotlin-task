package com.example.tasks.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.tasks.R
import com.example.tasks.business.PriorityBusiness
import kotlinx.android.synthetic.main.activity_task_form.*

class TaskFormActivity : AppCompatActivity() {

    private lateinit var mBussinesPriority : PriorityBusiness
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_form)

        mBussinesPriority = PriorityBusiness(this)
        loadPriorities()
    }

    private fun loadPriorities() {
        val lstPrioritiesEntities = mBussinesPriority.getList()

        //Converte toda a lista de entidade para uma lista de string em uma única linha
        val lstPriorities = lstPrioritiesEntities.map { it.description }
        val adapter = ArrayAdapter<String> (this,R.layout.support_simple_spinner_dropdown_item,lstPriorities)
        spinnerPriority.adapter = adapter
    }
}
