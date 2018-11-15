package com.loop.componentdemo.persistence

import androidx.room.*
import io.reactivex.Flowable

@Dao
interface UserDao {

    @Query("select * from users where userId = :id")
    fun getUserById(id:String): Flowable<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user:User)

    @Query("delete from users")
    fun deleteAllUsers()
}