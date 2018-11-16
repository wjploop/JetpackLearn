package com.loop.pagesample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.paging.Config
import androidx.paging.toLiveData

class CheeseViewModel(app:Application): AndroidViewModel(app){

    val dao=CheeseDb.get(app).cheeseDao()

    val allCheese=dao.allCheeseByName().toLiveData(Config(
        pageSize = 8,
        enablePlaceholders = true,
        maxSize = 200
    ))

    fun insert(text:CharSequence)= ioThread {
        dao.insert(Cheese(id = 0,name = text.toString()))
    }

    fun remove(cheese: Cheese)= ioThread {
        dao.delete(cheese)
    }
}