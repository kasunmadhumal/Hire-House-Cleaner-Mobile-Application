package com.example.hirehousecleaners.app_database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {


    @Query("SELECT * FROM currentuser")
    fun loadById(): List<Currentuser>

    @Insert
    fun insertAll(vararg currentuser: Currentuser)

    @Query("delete from  currentuser")
    fun deleteLastUser()

}