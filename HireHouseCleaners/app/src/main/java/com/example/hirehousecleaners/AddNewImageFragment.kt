package com.example.hirehousecleaners

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hirehousecleaners.databinding.FragmentAddNewImageBinding
import com.example.hirehousecleaners.databinding.FragmentFirstBinding


class AddNewImageFragment : Fragment() {

    private var _binding: FragmentAddNewImageBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddNewImageBinding.inflate(inflater, container, false)
        return binding.root

    }



}