package com.example.hirehousecleaners.authenticateUser

import android.app.Application
import android.app.ProgressDialog
import android.os.CountDownTimer
import android.widget.ProgressBar
import android.widget.Toast
import com.example.hirehousecleaners.app_database.AppDatabase
import com.example.hirehousecleaners.app_database.Currentuser
import com.example.hirehousecleaners.model.User
import com.example.hirehousecleaners.utils.MyApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class AuthenticateUser(var loginUserName:String,var loginPassword:String) :Thread() {

    @Override
    override fun run() {
        super.run()
        var db = FirebaseFirestore.getInstance()
        db.collection("users").get().addOnCompleteListener {
            if (it.isSuccessful) {

                var userList = ArrayList<User>()


                run breaking@{
                    it.result!!.forEach { q ->

                        var username = q.get("userName").toString()
                        var firstname = q.get("firstName").toString()
                        var lastname = q.get("lastName").toString()
                        var password = q.get("password").toString()
                        var role = q.get("role").toString()
                        var location = q.get("location").toString()



                        if (username == loginUserName && password == loginPassword) {
                             println(username)
                            var currentuser: Currentuser = Currentuser(
                                null,
                                username,
                                firstname,
                                lastname,
                                role,
                                location,
                                password
                            )

                            //save the user details in app database
                            val db = AppDatabase.getAppDatabase()
                            val userDao = db!!.UserDao()
                            userDao.insertAll(currentuser)
                            Toast.makeText(
                                MyApp.context,
                                "user login successfully.",
                                Toast.LENGTH_SHORT
                            ).show()

                            return@breaking


                        }


                    }


                }
            }
        }



    }


//    fun userAuthentication(loginUserName: String, loginPassword: String): String {
//
//        var pd = android.app.ProgressDialog(MyApp.context)
//        var ct = CountDownTimer()
//
//
//        var db = FirebaseFirestore.getInstance()
//        db.collection("users").get().addOnCompleteListener {
//            if (it.isSuccessful) {
//
//                var userList = ArrayList<User>()
//
//
//                run breaking@{
//                    it.result!!.forEach { q ->
//                        var username = q.get("userName").toString()
//                        var firstname = q.get("firstName").toString()
//                        var lastname = q.get("lastName").toString()
//                        var password = q.get("password").toString()
//                        var role = q.get("role").toString()
//
//                        if (username == loginUserName && password == loginPassword) {
//                            println(username)
//                            println(loginUserName)
//                            var currentuser: Currentuser = Currentuser(
//                                null,
//                                username,
//                                firstname,
//                                lastname,
//                                role
//                            )
//
//                            //save the user details in app database
//                            val db = AppDatabase.getAppDatabase()
//                            val userDao = db!!.UserDao()
//                            userDao.insertAll(currentuser)
//                            Toast.makeText(
//                                MyApp.context,
//                                "user login successfully.",
//                                Toast.LENGTH_SHORT
//                            ).show()
//
//                            return@breaking
//
//
//                        }
//
//
//                    }
//
//
//                }
//            }
//        }
//
//        return "done"
//
//    }

}