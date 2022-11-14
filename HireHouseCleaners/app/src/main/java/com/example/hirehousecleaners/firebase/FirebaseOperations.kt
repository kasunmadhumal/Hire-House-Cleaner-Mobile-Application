package com.example.hirehousecleaners.firebase

import android.app.Application
import android.widget.Toast
import androidx.navigation.NavController
import com.example.hirehousecleaners.R
import com.example.hirehousecleaners.authenticateUser.UserDetails
import com.example.hirehousecleaners.databinding.FragmentCustomerHomeScreenBinding
import com.example.hirehousecleaners.databinding.FragmentUpdateHouseInformationBinding
import com.example.hirehousecleaners.model.HouseInformation
import com.example.hirehousecleaners.model.Order
import com.example.hirehousecleaners.model.User
import com.example.hirehousecleaners.utils.MyApp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlin.reflect.typeOf

class FirebaseOperations :Application() {


    fun setHouseDetailsForView(binding:FragmentCustomerHomeScreenBinding) {
        var userDetails: ArrayList<String>? = UserDetails().getUserDetails()
        var usernameOfUser:String = userDetails?.get(3).toString()
        binding.welcomeText.text = "Welcome "+usernameOfUser


        var db = FirebaseFirestore.getInstance()
        db.collection("users").get().addOnCompleteListener {
            if (it.isSuccessful) {


                run breaking@{
                    it.result!!.forEach { q ->
                        var username = q.get("userName").toString()
                        var houseInformation = q.get("houseInformation")
                        val details = Gson().toJson(houseInformation)
                        val accessDetails = Gson().fromJson(details, HouseInformation::class.java)

                        if (username == usernameOfUser) {
                            binding.numOfRooms.text = accessDetails.rooms.toString()
                            binding.numOfBathrooms.text = accessDetails.bathrooms.toString()
                            binding.numOfKitchens.text = accessDetails.kitchens.toString()
                            binding.numOfMainFlow.text = accessDetails.mainFloors.toString()
                            binding.numOfKoridow.text = accessDetails.koridows.toString()
                            binding.numOfGarage.text = accessDetails.garages.toString()
                            binding.flootType.text =  accessDetails.typeOfFloor.toString()


                            return@breaking


                        }


                    }


                }
            }

        }

    }




    fun setHouseUpdateDetailsForView(
        binding: FragmentUpdateHouseInformationBinding
    ) {


        var userDetails: ArrayList<String>? = UserDetails().getUserDetails()
        var usernameOfUser:String = userDetails?.get(3).toString()


        var db = FirebaseFirestore.getInstance()
        db.collection("users").get().addOnCompleteListener {
            if (it.isSuccessful) {


                run breaking@{
                    it.result!!.forEach { q ->
                        var username = q.get("userName").toString()
                        var houseInformation = q.get("houseInformation")
                        val details = Gson().toJson(houseInformation)
                        val accessDetails = Gson().fromJson(details, HouseInformation::class.java)

                        if (username == usernameOfUser) {
                            binding.numRooms.setText(accessDetails.rooms.toString())
                            binding.numBathrooms.setText(accessDetails.bathrooms.toString())
                            binding.numOfKitchens.setText(accessDetails.kitchens.toString())
                            binding.numMainFloor.setText(accessDetails.mainFloors.toString())
                            binding.numOfKoridows.setText(accessDetails.koridows.toString())
                            binding.numOfGarages.setText(accessDetails.garages.toString())
                            binding.floorType.setText(accessDetails.typeOfFloor.toString())


                            return@breaking


                        }


                    }


                }
            }

        }

    }






    fun updateHouseDetails(user: User, findNavController: NavController)= CoroutineScope(Dispatchers.IO).launch{

        val personCollectionRef = Firebase.firestore.collection("users")

        // db.collection("users").document("gWRMkwbkzbfeFjWDwJzB").set(houseInformation)

        val personQuery = personCollectionRef
            .whereEqualTo("firstName",user.firstName)
            .whereEqualTo("password", user.password)
            .get()
            .await()

        if(personQuery.documents.isNotEmpty()) {
            for(document in personQuery) {

                //personCollectionRef.document(document.id).update("age", newAge).await()
                personCollectionRef.document(document.id).set(
                    user,
                    SetOptions.merge()
                ).await()



            }
        }

    }



    fun acceptOrder(order:Order,orderName:String)= CoroutineScope(Dispatchers.IO).launch{

             val orderCollectionRef = Firebase.firestore.collection("orders")


              val orderQuery = orderCollectionRef
                  .whereEqualTo("userName",orderName)
                  .get()
                  .await()

              if(orderQuery.documents.isNotEmpty()) {
                  for(document in orderQuery) {

                                //personCollectionRef.document(document.id).update("age", newAge).await()
                      orderCollectionRef.document(document.id).set(
                          order,
                          SetOptions.merge()
                          ).await()



                            }
                        }




                    }








}