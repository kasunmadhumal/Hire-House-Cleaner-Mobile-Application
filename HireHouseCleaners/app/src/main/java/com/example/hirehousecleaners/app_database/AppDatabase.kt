package com.example.hirehousecleaners.app_database

import android.widget.Toast
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hirehousecleaners.utils.MyApp


import java.sql.SQLException


@Database(entities = [Currentuser::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun UserDao(): UserDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getAppDatabase(): AppDatabase? {
            if (INSTANCE == null) {

                try {
                    INSTANCE = Room.databaseBuilder(
                        MyApp.context!!.applicationContext,
                        AppDatabase::class.java, "loginUser"
                    ).allowMainThreadQueries()
                        .build()

                } catch (exception:SQLException){
                    Toast.makeText(MyApp.context,"Can't create database", Toast.LENGTH_SHORT).show()
                }

            }
            return INSTANCE
        }

    }
}