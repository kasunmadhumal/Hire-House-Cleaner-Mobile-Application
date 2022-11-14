package com.example.hirehousecleaners

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.hirehousecleaners.authenticateUser.UserDetails
import com.example.hirehousecleaners.databinding.FragmentFirstBinding
import com.example.hirehousecleaners.databinding.FragmentItemsBinding
import com.example.hirehousecleaners.utils.MyApp
import com.google.firebase.storage.FirebaseStorage
import java.io.File


class ItemsFragment : Fragment() {

    private var _binding: FragmentItemsBinding? = null

    var countImage:Int = 1

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentItemsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var username:String = ""
        try {
            username = requireArguments().getString("username") as String
        }catch (e: Exception){
            println(e.message)
        }

        if(username=="" || username == null){
            val details = UserDetails().getUserDetails()
            username = details?.get(3).toString()
        }
        retrieveImage(username)
        if (countImage == 1){
            binding.preImage.visibility = View.GONE
        }
        binding.getImage.setOnClickListener{
            retrieveImage(username)
        }

        binding.preImage.setOnClickListener{
            retrievePreviewImage(username)
        }


    }


    fun retrieveImage(username:String){

        val storageRef = FirebaseStorage.getInstance().reference.child("images/$username/$countImage.jpg")
        val localFile = File.createTempFile("tempImage","jpg")
        countImage += 1
        storageRef.getFile(localFile).addOnSuccessListener{
            val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
            binding.imageView.setImageBitmap(bitmap)
        }

    }


    fun retrievePreviewImage(username: String){
        val storageRef = FirebaseStorage.getInstance().reference.child("images/$username/$countImage.jpg")
        val localFile = File.createTempFile("tempImage","jpg")
        countImage -= 1
        storageRef.getFile(localFile).addOnSuccessListener{
            val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
            binding.imageView.setImageBitmap(bitmap)
        }

    }



}