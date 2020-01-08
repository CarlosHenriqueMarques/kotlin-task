package com.example.tasks.views

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import com.example.tasks.R
import com.example.tasks.business.PriorityBusiness
import com.example.tasks.business.TaskBusiness
import com.example.tasks.constants.TaskConstants
import com.example.tasks.entities.PriorityEntity
import com.example.tasks.entities.TaskEntity
import com.example.tasks.util.SecurityPreferences
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_task_form.*
import java.text.SimpleDateFormat
import java.util.*

class TaskFormActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private lateinit var mBussinesPriority : PriorityBusiness
    private lateinit var mBussinesTasks : TaskBusiness
    private lateinit var mSecuretyPreferences: SecurityPreferences
    private val mSimpleDateFormat : SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
    private var mLstPrioritiesEntity : MutableList<PriorityEntity> = mutableListOf()
    private var mLstPrioritiesId : MutableList<Int> = mutableListOf()
    private var mTaskId : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_form)

        mSecuretyPreferences = SecurityPreferences(this)
        mBussinesPriority = PriorityBusiness(this)
        mBussinesTasks = TaskBusiness(this)
        loadPriorities()
        loadDateFromActivity()

        buttonDate.setOnClickListener {
            openCalendar()
        }

        buttonTask.setOnClickListener {
            handleSave()
        }

    }

    private fun loadDateFromActivity() {
        val bundle = intent.extras
        if(bundle != null){
            mTaskId =  bundle.getInt(TaskConstants.BUNDLE.TASKID)
            val task = mBussinesTasks.get(mTaskId)

            if(task != null){
                editDescription.setText(task.description)
                buttonDate.text = task.dueDate
                checkComplete.isChecked = task.complete
                spinnerPriority.setSelection(getIndex(task.priorityId))
                buttonTask.text = getString(R.string.atualizar_task)
            }


        }
    }

    private fun handleSave() {
        try {
            val descriptionText = editDescription.text.toString()
            val priorityId = mLstPrioritiesId[spinnerPriority.selectedItemPosition]
            val complete = checkComplete.isChecked
            val duedate = buttonDate.text.toString()
            val userId = mSecuretyPreferences.getStoreString(TaskConstants.KEY.USER_ID)?.toInt()
            val taskEntity = TaskEntity(mTaskId,userId,priorityId,descriptionText,duedate,complete)

            if(mTaskId == 0){
                mBussinesTasks.insert(taskEntity)
                Toast.makeText(this,getString(R.string.insert_task_sucess), Toast.LENGTH_LONG).show()
            }else{
                mBussinesTasks.update(taskEntity)
                Toast.makeText(this,getString(R.string.update_task_sucess), Toast.LENGTH_LONG).show()
            }

            finish()

        }catch (ex : Exception){
            Toast.makeText(this, getString(R.string.erro_inesperado), Toast.LENGTH_LONG).show()
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

    private fun getIndex(id : Int): Int {
        var index = 0
        for (i in 0..mLstPrioritiesEntity.size){
            if(mLstPrioritiesEntity[i].id == id){
                var index = i
                break
            }
        }
        return index
    }
    private fun loadPriorities() {
        mLstPrioritiesEntity = mBussinesPriority.getList()

        //Converte toda a lista de entidade para uma lista de string em uma única linha
        val lstPriorities = mLstPrioritiesEntity.map { it.description }
        mLstPrioritiesId = mLstPrioritiesEntity.map { it.id }.toMutableList()

        val adapter = ArrayAdapter<String> (this,R.layout.support_simple_spinner_dropdown_item,lstPriorities)
        spinnerPriority.adapter = adapter
    }


}
