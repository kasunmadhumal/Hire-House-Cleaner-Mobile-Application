package com.example.hirehousecleaners

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.hirehousecleaners.authenticateUser.UserDetails
import com.example.hirehousecleaners.databinding.FragmentAddFeedBackBinding
import com.example.hirehousecleaners.databinding.FragmentFirstBinding
import com.example.hirehousecleaners.model.Feedback
import com.example.hirehousecleaners.model.HouseInformation
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson


class AddFeedBackFragment : Fragment() {

    private var _binding: FragmentAddFeedBackBinding? = null

    var db = FirebaseFirestore.getInstance()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddFeedBackBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var userDetails = UserDetails().getUserDetails()
        var sender = userDetails?.get(3).toString()
        binding.feedBackSender.text = sender
        var role = userDetails?.get(2).toString()

        if(role == "customer"){

            var db = FirebaseFirestore.getInstance()
            db.collection("orders").get().addOnCompleteListener {
                if (it.isSuccessful) {
                     it.result!!.forEach { q ->
                            var username = q.get("userName").toString()
                            var location = q.get("location").toString()
                            var date = q.get("date").toString()
                            if (username == sender){
                                var receiver = q.get("cleaner").toString()
                                binding.feedbackReciever.text = receiver
                                binding.feedbackLocation.text = location
                                binding.feedbackOrderDate.text = date

                            }

                    }
                }

            }

        }else if (role == "cleaner"){
             val details = requireArguments().getStringArrayList("feedback") as ArrayList
             binding.feedbackReciever.text = details[2].toString()
             binding.feedbackLocation.text = details[0].toString()
             binding.feedbackOrderDate.text = details[1].toString()

        }

        binding.sendFeedback.setOnClickListener {

            var rs = binding.feedbackReciever.text.toString()
            var location = binding.feedbackLocation.text.toString()
            var date = binding.feedbackOrderDate.text.toString()
            var message = binding.feedBackInput.text.toString()
            var feedback:Feedback = Feedback(sender,location,date, rs,message)

            if(rs != ""){
                db.collection("feedbacks").document().set(feedback)
                Toast.makeText(view.context,"Feedback successfully send", Toast.LENGTH_SHORT).show()
            }

        }





    }

}