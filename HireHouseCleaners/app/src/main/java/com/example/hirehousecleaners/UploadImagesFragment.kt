package com.example.hirehousecleaners

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hirehousecleaners.databinding.FragmentUploadImagesBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage


class UploadImagesFragment : Fragment() {


    private var _binding: FragmentUploadImagesBinding? = null
    // creating a storage reference
    var storageRef = Firebase.storage.reference


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUploadImagesBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnViewAll.setOnClickListener{
              findNavController().navigate(R.id.action_UploadImagesFragment_to_ItemsFragment)
        }

        binding.btnAddInfo.setOnClickListener{
            findNavController().navigate(R.id.action_UploadImagesFragment_to_AddNewImageFragment)
        }




    }




}