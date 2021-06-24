package com.example.todolist

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao {

    @Insert
    suspend fun insertTask(todoModel: TodoModel): Long

    @Query("SELECT * from TodoModel where isFinished == 0")
    fun getTask(): LiveData<List<TodoModel>>

    @Query("UPDATE TodoModel set isFinished = 1 where id =:uid")
    fun finishTask(uid: Long)

    @Query("DELETE from TodoModel where id =:uid")
    fun deleteTask(uid: Long)
}