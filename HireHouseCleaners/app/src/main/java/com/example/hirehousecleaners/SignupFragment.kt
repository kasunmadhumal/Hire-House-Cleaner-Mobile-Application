package com.example.hirehousecleaners

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.hirehousecleaners.databinding.FragmentSecondBinding
import com.example.hirehousecleaners.model.HouseInformation
import com.example.hirehousecleaners.model.User
import com.google.firebase.firestore.FirebaseFirestore


class SignupFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signupSignupButton.setOnClickListener {

            val username:String  = binding.signUpUserUsername.text.toString()
            val firstName:String = binding.signUpUserFirstName.text.toString()
            val lastname:String = binding.signUpUserLastName.text.toString()
            val location:String = binding.signUpUserLocation.text.toString()
            val password:String = binding.signUpUserPassword.text.toString()
            val conformPassword:String = binding.signUpUserConformPassword.text.toString()
            val customerCheckbox:Boolean = binding.signupStudentCheckbox.isChecked
            val cleanerCheckBox:Boolean = binding.signupLecturerCheckbox.isChecked
            var role:String = ""


            if(username=="" || firstName =="" || lastname == "" || location == "" || conformPassword == "" || password == "")
            {
                Toast.makeText(view.context,"Fill Blank Details", Toast.LENGTH_SHORT).show()
            }else if(!customerCheckbox && !cleanerCheckBox){
                Toast.makeText(view.context,"Select User Role", Toast.LENGTH_SHORT).show()
            }else if(customerCheckbox && cleanerCheckBox){
                Toast.makeText(view.context,"Only select One user Role", Toast.LENGTH_SHORT).show()
            }else if(password != conformPassword){
                Toast.makeText(view.context,"Confirm Password Not Matching", Toast.LENGTH_SHORT).show()
            }else{

                role = if(customerCheckbox){
                    "customer"
                }else if (cleanerCheckBox){
                    "cleaner"
                }else{
                    "not added"
                }



                var houseInformation = HouseInformation(0,0,0,0,0,0,"")
                var user = User(username,firstName,lastname,location,password,role,houseInformation)
                db.collection("users").document().set(user)


                findNavController().navigate(R.id.action_SignupFragment_to_LoginFragment)


            }




        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}