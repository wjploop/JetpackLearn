package com.loop.componentdemo

import android.content.Context
import com.loop.componentdemo.persistence.UserDao
import com.loop.componentdemo.persistence.UsersDatabase
import com.loop.componentdemo.ui.UserViewModelFactory


//之后会用dagger代替注入
object Injection{

    fun provideDataSource(context: Context):UserDao{
        val database=UsersDatabase.getInstane(context)
        return database.userDao()
    }

    fun provideViewModelFactory(context: Context):UserViewModelFactory{
        val database=UsersDatabase.getInstane(context)
        return UserViewModelFactory(database.userDao())
    }

}