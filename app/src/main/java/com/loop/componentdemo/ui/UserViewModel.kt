package com.loop.componentdemo.ui

import androidx.lifecycle.ViewModel
import com.loop.componentdemo.persistence.User
import com.loop.componentdemo.persistence.UserDao
import io.reactivex.Completable
import io.reactivex.Flowable

class UserViewModel(private val dataSource:UserDao):ViewModel() {

    /**
     *  get the username of user
     *
     *  @return a [Flowable] that will emit every time the user has been updated
     */
    fun userName(): Flowable<String> =
        dataSource.getUserById("1").map(User::username)


    fun updateUser(userName:String):Completable{

        return Completable.fromAction{
            val user= User("1",userName)
            dataSource.insertUser(user)
        }
    }

}