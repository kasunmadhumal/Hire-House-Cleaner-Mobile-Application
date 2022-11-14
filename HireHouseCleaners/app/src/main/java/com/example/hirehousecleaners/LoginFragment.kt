package com.example.hirehousecleaners

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.hirehousecleaners.app_database.AppDatabase
import com.example.hirehousecleaners.app_database.Currentuser
import com.example.hirehousecleaners.authenticateUser.AuthenticateUser
import com.example.hirehousecleaners.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class LoginFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.buttonSignup.setOnClickListener {
            findNavController().navigate(R.id.action_LoginFragment_to_SignupFragment)
        }

        binding.buttonLogin.setOnClickListener{
            var username:String = binding.username.text.toString()
            var password:String = binding.password.text.toString()
            if(username!="" && password != ""){


                var obj = AuthenticateUser(username,password)
                obj.run()

                val db = AppDatabase.getAppDatabase()
                val userDao = db!!.UserDao()
                val currentuser:List<Currentuser> = userDao.loadById()

                if(currentuser.isNotEmpty()){
                    println(currentuser[0].role)
                    when (currentuser[0].role) {
                        "customer" -> {
                            findNavController().navigate(R.id.action_LoginFragment_to_CustomerHomeScreenFragment)
                        }
                        "admin" -> {
                            findNavController().navigate(R.id.action_LoginFragment_to_AdminHomePageFragment)
                        }
                        "cleaner" -> {
                            findNavController().navigate(R.id.action_LoginFragment_to_CleanerHomePageFragment)
                        }
                    }


                }else{

                           Toast.makeText(view.context,"Invalid UserName or Password", Toast.LENGTH_SHORT).show()

                }




            }else if(username == "" && password==""){
                Toast.makeText(view.context,"please provide user email and password", Toast.LENGTH_SHORT).show()

            }else if(username == ""){
                Toast.makeText(view.context,"please provide user email", Toast.LENGTH_SHORT).show()

            }else if(password==""){
                Toast.makeText(view.context,"please provide user password", Toast.LENGTH_SHORT).show()

            }


        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}