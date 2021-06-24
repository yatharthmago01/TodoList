package com.example.todolist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TodoModel :: class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object{

        @Volatile
        private var INSTANCE: AppDatabase? = null

        // For obtaining object of type AppDatabase
        fun getDatabase(context: Context): AppDatabase? {
            var tempInstance = INSTANCE

            if(tempInstance != null)
                return tempInstance

            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, DB_NAME).build()
                INSTANCE = instance
                return INSTANCE
            }
        }
    }
}