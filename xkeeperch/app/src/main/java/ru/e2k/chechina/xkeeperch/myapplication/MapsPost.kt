package ru.e2k.chechina.xkeeperch.myapplication

import java.util.*

class MapsPost private constructor(){
    val gsmPoints: MutableList<Post>
    init{
        gsmPoints = ArrayList()
    }
    fun add(gsmPoint: Post?) {
        if(gsmPoint != null)
            gsmPoints.add(gsmPoint)
    }

    companion object {
        val instance = MapsPost()
    }

}