package com.example.hirehousecleaners

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hirehousecleaners.databinding.FragmentFirstBinding
import com.example.hirehousecleaners.databinding.FragmentImageDetailsBinding


class ImageDetailsFragment : Fragment() {


    private var _binding: FragmentImageDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentImageDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }



}