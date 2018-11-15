package com.loop.componentdemo.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import java.util.*

@Entity(tableName = "users")
data class User(
    @ColumnInfo(name="userId")
    val id:String = UUID.randomUUID().toString(),
    val username:String
)