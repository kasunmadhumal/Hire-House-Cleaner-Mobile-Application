package com.example.hirehousecleaners.authenticateUser

import com.example.hirehousecleaners.app_database.AppDatabase
import com.example.hirehousecleaners.app_database.Currentuser

class UserDetails {

    fun getUserDetails(): ArrayList<String>? {

        var userDetails = ArrayList<String>()
        val db = AppDatabase.getAppDatabase()
        val userDao = db!!.UserDao()
        val currentuser:List<Currentuser> = userDao.loadById()
        userDetails?.add(currentuser[0].firstname)
        userDetails?.add(currentuser[0].lastname)
        userDetails?.add(currentuser[0].role)
        userDetails?.add(currentuser[0].username)
        userDetails?.add(currentuser[0].password)
        userDetails?.add(currentuser[0].location)

        return userDetails


    }
}