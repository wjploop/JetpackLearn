package com.loop.pagesample

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CheeseDao {

    /**
     *  Room knows how to return a LivePagedListProvider, from which we can get a liveData and serve it back to UI
     *  via viewModel
     */
    @Query("select * from cheese order by name collate NOCASE ASC")
    fun allCheeseByName(): DataSource.Factory<Int,Cheese>

    @Insert
    fun insert(cheese: Cheese)

    @Insert
    fun insert(cheeseList:List<Cheese>)

    @Delete
    fun delete(cheese: Cheese)
}