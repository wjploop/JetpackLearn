package com.loop.componentdemo

import android.content.Context
import com.loop.componentdemo.persistence.UserDao
import com.loop.componentdemo.persistence.UsersDatabase
import com.loop.componentdemo.ui.UserViewModelFactory


//之后会用dagger代替注入
object Injection{

    private fun provideDataSource(context: Context):UserDao{
        val database=UsersDatabase.getInstance(context)
        return database.userDao()
    }

    fun provideViewModelFactory(context: Context):UserViewModelFactory{
        val dataSource= provideDataSource(context)
        return UserViewModelFactory(dataSource)
    }

}