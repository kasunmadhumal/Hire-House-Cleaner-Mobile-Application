package com.example.hirehousecleaners

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hirehousecleaners.app_database.AppDatabase
import com.example.hirehousecleaners.authenticateUser.UserDetails
import com.example.hirehousecleaners.databinding.FragmentAdminHomePageBinding
import com.example.hirehousecleaners.model.HouseInformation
import com.example.hirehousecleaners.model.Order
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson


class AdminHomePageFragment : Fragment() {


    private var _binding: FragmentAdminHomePageBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAdminHomePageBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.orderListRecyclerView.layoutManager = LinearLayoutManager(view.context)
        setOrderListView()

        binding.adminLogoutButton.setOnClickListener {
            val db = AppDatabase.getAppDatabase()
            val userDao = db!!.UserDao()
            userDao.deleteLastUser()
            findNavController().navigate(R.id.action_AdminHomePageFragment_to_LoginFragment)
        }


    }


    fun setOrderListView(){


        var userDetails: ArrayList<String>? = UserDetails().getUserDetails()
        var listOfOrders = ArrayList<Order>()


        var db = FirebaseFirestore.getInstance()
        db.collection("orders").get().addOnCompleteListener {
            if (it.isSuccessful) {



                    it.result!!.forEach { q ->
                        var username = q.get("userName").toString()
                        var houseInformation = q.get("houseInformation")
                        val details = Gson().toJson(houseInformation)
                        val accessDetails = Gson().fromJson(details, HouseInformation::class.java)
                        var date = q.get("date").toString()
                        var cleaner = q.get("cleaner").toString()
                        var budget = q.get("budget").toString()
                        var location = q.get("location").toString()

                        var hifmtn = HouseInformation(accessDetails.rooms,
                                                      accessDetails.bathrooms,
                                                      accessDetails.kitchens,
                                                      accessDetails.mainFloors,
                                                      accessDetails.koridows,
                                                      accessDetails.garages,
                                                      accessDetails.typeOfFloor
                        )



                        var order = Order(username,
                                          location,
                                          date,
                                           cleaner,
                                          budget.toInt(),
                                          hifmtn)

                        listOfOrders.add(order)
                        binding.orderListRecyclerView.adapter = OrderListViewAdapter(listOfOrders,findNavController())








                    }



            }

        }


        binding.orderListRecyclerView.adapter = OrderListViewAdapter(listOfOrders,findNavController())





    }



}




class OrderListViewAdapter(private val orders: List<Order>, private val findNavController: NavController): RecyclerView.Adapter<CustomViewHolderOrderList>(){



    //number of Items
    override fun getItemCount(): Int {
        return orders.count()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolderOrderList {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.one_order_view_design,parent,false)
        return CustomViewHolderOrderList(cellForRow,findNavController)
    }

    override fun onBindViewHolder(holder: CustomViewHolderOrderList, position: Int) {

        holder.username.text  = orders.elementAt(position).userName
        holder.location.text = orders.elementAt(position).location


    }
}




class CustomViewHolderOrderList(val view: View, private val findNavController: NavController): RecyclerView.ViewHolder(view){
    val username: TextView =    itemView.findViewById(R.id.username_id)
    val location: TextView = itemView.findViewById(R.id.order_location_id)



    init{
        view.setOnClickListener{
            findNavController.navigate(R.id.action_AdminHomePageFragment_to_AddPaymentFragment, Bundle().apply {
                putString("username",username.text.toString())
            })

        }



    }

}