package com.example.tasks.entities

interface OnTaskListFragmentInteractionListener {
    fun onClick(taskId : Int)

    fun onDeleteClick(taskId : Int)
    fun onUncompleteClick(id: Int)
    fun onCompleteClick(id: Int)




}