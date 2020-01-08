package com.example.tasks.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tasks.R
import com.example.tasks.adapter.TaskListAdapter
import com.example.tasks.business.TaskBusiness
import com.example.tasks.constants.TaskConstants
import com.example.tasks.entities.OnTaskListFragmentInteractionListener
import com.example.tasks.util.SecurityPreferences
import kotlinx.android.synthetic.main.fragment_task_list.view.*


class TaskListFragment : Fragment() {
    private lateinit var mContext: Context
    private lateinit var mRecyclerTaskList : RecyclerView
    private lateinit var mTaskBussines : TaskBusiness
    private lateinit var mSecurityPreferences : SecurityPreferences
    private var mTaskFilter: Int = 0
    private lateinit var mListener : OnTaskListFragmentInteractionListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(arguments != null){
            mTaskFilter = arguments!!.getInt(TaskConstants.TASKFILTER.KEY)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        val view : View = inflater.inflate(R.layout.fragment_task_list, container, false)
        mContext = view.context
        mTaskBussines = TaskBusiness(mContext)
        mSecurityPreferences = SecurityPreferences(mContext)

       //Edição de tarefa
        mListener = object : OnTaskListFragmentInteractionListener {
            override fun onClick(taskId: Int) {
                val bundle = Bundle()
                bundle.putInt(TaskConstants.BUNDLE.TASKID,taskId)
                val intent = Intent(activity, TaskFormActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDeleteClick(taskId: Int) {
                mTaskBussines.delete(taskId)
                loadTask()
                Toast.makeText(mContext,getString(R.string.delete_task),Toast.LENGTH_LONG).show()
            }

            override fun onUncompleteClick(id: Int) {
                mTaskBussines.complete(id,false)
                loadTask()
            }

            override fun onCompleteClick(id: Int) {
                mTaskBussines.complete(id, true)
                loadTask()
            }
        }

        view.floatAddTask.setOnClickListener {
            val intent = Intent(activity, TaskFormActivity::class.java)
           startActivity(intent)
        }

        mRecyclerTaskList = view.findViewById(R.id.recycleTask)
        mRecyclerTaskList.adapter = TaskListAdapter(mutableListOf(),mListener)
        view.recycleTask.layoutManager = LinearLayoutManager(mContext)

        return view
    }

    override fun onResume() {
        super.onResume()
        loadTask()
    }

    private fun loadTask() {
        mRecyclerTaskList.adapter = TaskListAdapter(mTaskBussines.getList(mTaskFilter),mListener)
    }

    companion object {
        fun newInstance(taskFilter : Int) : TaskListFragment {
            val args = Bundle()
            args.putInt(TaskConstants.TASKFILTER.KEY,taskFilter)
            val fragment = TaskListFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
