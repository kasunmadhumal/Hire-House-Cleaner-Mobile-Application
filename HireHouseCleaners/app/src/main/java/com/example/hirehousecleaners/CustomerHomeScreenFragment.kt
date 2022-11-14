package com.example.hirehousecleaners

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.hirehousecleaners.app_database.AppDatabase
import com.example.hirehousecleaners.authenticateUser.UserDetails
import com.example.hirehousecleaners.databinding.FragmentCustomerHomeScreenBinding
import com.example.hirehousecleaners.firebase.FirebaseOperations
import com.example.hirehousecleaners.model.HouseInformation
import com.example.hirehousecleaners.model.Order
import com.example.hirehousecleaners.utils.MyApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import java.time.LocalDate
import java.time.LocalDateTime


class CustomerHomeScreenFragment : Fragment() {


    private var _binding: FragmentCustomerHomeScreenBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCustomerHomeScreenBinding.inflate(inflater, container, false)
        FirebaseOperations().setHouseDetailsForView(binding)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.logoutButton.setOnClickListener {
            val db = AppDatabase.getAppDatabase()
            val userDao = db!!.UserDao()
            userDao.deleteLastUser()
            findNavController().navigate(R.id.action_CustomerHomeScreenFragment_to_LoginFragment)
        }


        binding.editHomeDetailsButton.setOnClickListener{
            findNavController().navigate(R.id.action_CustomerHomeScreenFragment_to_UpdateHouseInformationFragment)

        }

        binding.updateHouseImagesButton.setOnClickListener{
            findNavController().navigate(R.id.action_CustomerHomeScreenFragment_to_UploadImagesFragment)
        }

        binding.feedbackForCleanerButton.setOnClickListener {
            findNavController().navigate(R.id.action_CustomerHomeScreenFragment_to_AddFeedBackFragment)
        }

        binding.sendOrderButton.setOnClickListener{

            var rooms = binding.numOfRooms.text.toString()
            var bathrooms = binding.numOfBathrooms.text.toString()
            var kitchens = binding.numOfKitchens.text.toString()
            var mainfoors = binding.numOfMainFlow.text.toString()
            var koridows = binding.numOfKoridow.text.toString()
            var garage = binding.numOfGarage.text.toString()
            var typeOfFloors = binding.flootType.text.toString()

            var houseInformation:HouseInformation = HouseInformation(rooms.toInt(), bathrooms.toInt(), kitchens.toInt(),
            mainfoors.toInt(), koridows.toInt(),garage.toInt(),typeOfFloors)

            var userDetails = UserDetails().getUserDetails()
            var username = userDetails?.get(3).toString()
            var location = userDetails?.get(5).toString()
            var date = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                LocalDateTime.now()
            } else {
                TODO("VERSION.SDK_INT < O")
            }

            var order = Order(username,location,date.toString(),"",0,houseInformation)

            var db = FirebaseFirestore.getInstance()

            db.collection("orders").document().set(order)

            Toast.makeText(view.context,"Order send", Toast.LENGTH_SHORT).show()


        }

    }


}