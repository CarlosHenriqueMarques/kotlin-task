package com.example.tasks.views

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.DatePicker
import com.example.tasks.R
import com.example.tasks.business.PriorityBusiness
import kotlinx.android.synthetic.main.activity_task_form.*
import java.text.SimpleDateFormat
import java.util.*

class TaskFormActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private lateinit var mBussinesPriority : PriorityBusiness
    private val mSimpleDateFormat : SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_form)

        mBussinesPriority = PriorityBusiness(this)
        loadPriorities()

        buttonDate.setOnClickListener {
            openCalendar()
        }
    }

    private fun openCalendar() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val dayOfMonth = c.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this,this,year,month,dayOfMonth).show()
    }

    override fun onDateSet(view: DatePicker?, years: Int, month: Int, dayOfMonths: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(years,month,dayOfMonths)
        buttonDate.text = mSimpleDateFormat.format(calendar.time)
    }

    private fun loadPriorities() {
        val lstPrioritiesEntities = mBussinesPriority.getList()

        //Converte toda a lista de entidade para uma lista de string em uma única linha
        val lstPriorities = lstPrioritiesEntities.map { it.description }
        val adapter = ArrayAdapter<String> (this,R.layout.support_simple_spinner_dropdown_item,lstPriorities)
        spinnerPriority.adapter = adapter
    }


}
