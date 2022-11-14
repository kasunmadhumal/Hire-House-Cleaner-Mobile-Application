package com.example.hirehousecleaners.app_database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.w3c.dom.Text

@Entity
data class Currentuser(
    @PrimaryKey(autoGenerate = true) val id:Int?,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "firstname") val firstname:String,
    @ColumnInfo(name = "lastname") val lastname:String,
    @ColumnInfo(name = "role") val role: String,
    @ColumnInfo(name = "location") val location: String,
    @ColumnInfo(name = "password") val password: String
)