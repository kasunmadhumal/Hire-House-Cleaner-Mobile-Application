package com.example.hirehousecleaners

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.hirehousecleaners.authenticateUser.UserDetails
import com.example.hirehousecleaners.databinding.FragmentAddPaymentBinding
import com.example.hirehousecleaners.model.HouseInformation
import com.example.hirehousecleaners.model.Order
import com.example.hirehousecleaners.utils.MyApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AddPaymentFragment : Fragment() {

    private var _binding: FragmentAddPaymentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentAddPaymentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            val username = requireArguments().getString("username") as String
            setPaymentPage(username,binding)

            binding.homeImagesButton.setOnClickListener {
                findNavController().navigate(R.id.action_AddPaymentFragment_to_ItemsFragment, Bundle().apply {
                    putString("username",username)
                })
            }

            binding.feedbackClearner.setOnClickListener {

                val location = binding.cLocation.text.toString()
                val date = binding.cPostedDate.text.toString()
                val receiver = binding.cUsername.text.toString()

                var feedback  = ArrayList<String>()
                feedback.add(location)
                feedback.add(date)
                feedback.add(receiver)


                findNavController().navigate(R.id.action_AddPaymentFragment_to_AddFeedBackFragment, Bundle().apply {
                    putStringArrayList("feedback",feedback)
                })

            }

        binding.submitPayment.setOnClickListener {
                    submitOrder()
        }


    }



    fun submitOrder()= CoroutineScope(Dispatchers.IO).launch{


        var rooms = binding.nRooms.text.toString()
        var bathrooms = binding.nBathrooms.text.toString()
        var kitchens = binding.nKitchens.text.toString()
        var mainfoors = binding.nMainFloor.text.toString()
        var koridows = binding.nKoridows.text.toString()
        var garage = binding.nGarages.text.toString()
        var typeOfFloors = binding.hTypeOfFloor.text.toString()

        var houseInformation:HouseInformation = HouseInformation(rooms.toInt(), bathrooms.toInt(), kitchens.toInt(),
            mainfoors.toInt(), koridows.toInt(),garage.toInt(),typeOfFloors)

        var username = binding.cUsername.text.toString()
        var location = binding.cLocation.text.toString()
        var date = binding.cPostedDate.text.toString()
        var budget = binding.housePaymentInput.text.toString()

        var order = Order(username,location,date,"",budget.toInt(),houseInformation)

        val orderCollectionRef = Firebase.firestore.collection("orders")


        val orderQuery = orderCollectionRef
            .whereEqualTo("userName",username)
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



    fun setPaymentPage(c_username: String, binding: FragmentAddPaymentBinding){

        var user = UserDetails().getUserDetails()
        var role = user?.get(2)
        if(role == "cleaner"){
            binding.submitPayment.visibility = View.GONE
            binding.housePaymentInput.visibility = View.GONE
            binding.textView26.visibility = View.GONE
        }else if(role == "admin"){
            binding.feedbackClearner.visibility = View.GONE
        }




    var db = FirebaseFirestore.getInstance()
    db.collection("orders").get().addOnCompleteListener {
        if (it.isSuccessful) {



            it.result!!.forEach { q ->
                var username = q.get("userName").toString()

                if (c_username == username) {
                    var houseInformation = q.get("houseInformation")
                    val details = Gson().toJson(houseInformation)
                    val accessDetails = Gson().fromJson(details, HouseInformation::class.java)
                    var date = q.get("date").toString()
                    var cleaner = q.get("cleaner").toString()
                    var budget = q.get("budget").toString().toInt()
                    var location = q.get("location").toString()

                    binding.cUsername.text = username
                    binding.cPostedDate.text = date
                    binding.cLocation.text = location


                    binding.nRooms.text = accessDetails.rooms.toString()
                    binding.nBathrooms.text = accessDetails.bathrooms.toString()
                    binding.nKitchens.text= accessDetails.kitchens.toString()
                    binding.nMainFloor.text= accessDetails.mainFloors.toString()
                    binding.nKoridows.text= accessDetails.koridows.toString()
                    binding.nGarages.text= accessDetails.garages.toString()
                    binding.hTypeOfFloor.text= accessDetails.typeOfFloor.toString()




                }

            }



        }

    }

}



}