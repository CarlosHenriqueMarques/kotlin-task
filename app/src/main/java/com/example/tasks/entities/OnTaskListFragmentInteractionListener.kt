package com.example.tasks.entities

interface OnTaskListFragmentInteractionListener {
    fun onClick(taskId : Int)

    fun onDeleteClick(taskId : Int)
}