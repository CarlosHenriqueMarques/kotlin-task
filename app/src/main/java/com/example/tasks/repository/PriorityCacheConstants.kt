package com.example.tasks.repository

import com.example.tasks.entities.PriorityEntity

class PriorityCacheConstants private constructor(){
    companion object{
        fun setCache(list : List<PriorityEntity>){

            for(item in list){
                mPriorityCache.put(item.id,item.description)
            }
        }

        fun getPriorityDescription(id : Int) : String{
            return if(mPriorityCache[id] == null){
                ""
            }else{
                mPriorityCache[id].toString()
            }

        }
        private val mPriorityCache = hashMapOf<Int, String>()
    }
}