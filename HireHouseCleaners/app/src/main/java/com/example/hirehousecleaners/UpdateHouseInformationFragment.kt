package com.example.hirehousecleaners

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.hirehousecleaners.authenticateUser.UserDetails
import com.example.hirehousecleaners.databinding.FragmentUpdateHouseInformationBinding
import com.example.hirehousecleaners.firebase.FirebaseOperations
import com.example.hirehousecleaners.model.HouseInformation
import com.example.hirehousecleaners.model.User


class UpdateHouseInformationFragment : Fragment() {

    private var _binding: FragmentUpdateHouseInformationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUpdateHouseInformationBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        FirebaseOperations().setHouseUpdateDetailsForView(binding)


        binding.updateHomeDetailsButton.setOnClickListener{
            var rooms = binding.numRooms.text.toString()
            var bathrooms = binding.numBathrooms.text.toString()
            var kitchens = binding.numOfKitchens.text.toString()
            var mainFloor = binding.numMainFloor.text.toString()
            var koridows = binding.numOfKoridows.text.toString()
            var garages = binding.numOfGarages.text.toString()
            var floorType = binding.floorType.text.toString()
            var houseInformation:HouseInformation = HouseInformation(rooms.toInt(), bathrooms.toInt(),kitchens.toInt()
                ,mainFloor.toInt(),koridows.toInt(),garages.toInt(),floorType)

            var dtl: ArrayList<String>? = UserDetails().getUserDetails()
            var uname = dtl?.get(3).toString()
            var fname = dtl?.get(0).toString()
            var lname = dtl?.get(1).toString()
            var lcation = dtl?.get(5).toString()
            var pswd = dtl?.get(4).toString()
            var rl = dtl?.get(2).toString()


            var user = User(uname,fname,lname,lcation,pswd,rl,houseInformation)
            FirebaseOperations().updateHouseDetails(user,findNavController())
            Toast.makeText(view.context,"Successfully Updated", Toast.LENGTH_SHORT).show()



        }



    }


}