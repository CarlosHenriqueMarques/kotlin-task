package com.example.tasks.business

import android.content.Context
import com.example.tasks.entities.PriorityEntity
import com.example.tasks.repository.PriorityRepository

class PriorityBusiness (context: Context) {
    private val mPriorityRepository : PriorityRepository = PriorityRepository.getInstance(context)

    fun getList() : MutableList<PriorityEntity>{
        return  mPriorityRepository.getList()
    }
}