package com.example.hirehousecleaners

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hirehousecleaners.app_database.AppDatabase
import com.example.hirehousecleaners.authenticateUser.UserDetails
import com.example.hirehousecleaners.databinding.FragmentClearnerHomePageBinding
import com.example.hirehousecleaners.databinding.FragmentFirstBinding
import com.example.hirehousecleaners.firebase.FirebaseOperations
import com.example.hirehousecleaners.model.HouseInformation
import com.example.hirehousecleaners.model.Order
import com.example.hirehousecleaners.utils.MyApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class CleanerHomePageFragment : Fragment() {
    private var _binding: FragmentClearnerHomePageBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentClearnerHomePageBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cleanerOrderListRecyclerView.layoutManager = LinearLayoutManager(view.context)
        setCleanerOrderListView()

        binding.cleanerLogoutButton.setOnClickListener {
            val db = AppDatabase.getAppDatabase()
            val userDao = db!!.UserDao()
            userDao.deleteLastUser()
            findNavController().navigate(R.id.action_CleanerHomePageFragment_to_LoginFragment)
        }
    }


    fun setCleanerOrderListView(){


        var userDetails: ArrayList<String>? = UserDetails().getUserDetails()
        var cleanerName = userDetails?.get(3)
        var cleanerLocation = userDetails?.get(5)
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

                    if(cleanerLocation == location && (cleaner == "" || cleaner == cleanerName)){

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
                        binding.cleanerOrderListRecyclerView.adapter = CleanerOrderListViewAdapter(listOfOrders,findNavController())


                    }
                }



            }

        }

    }


}

class CleanerOrderListViewAdapter(private val orders: List<Order>, private val findNavController: NavController): RecyclerView.Adapter<CleanerCustomViewHolderOrderList>(){



    //number of Items
    override fun getItemCount(): Int {
        return orders.count()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CleanerCustomViewHolderOrderList {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.cleaner_one_item_view,parent,false)
        return CleanerCustomViewHolderOrderList(cellForRow,findNavController)
    }

    override fun onBindViewHolder(holder: CleanerCustomViewHolderOrderList, position: Int) {

        holder.username.text  = orders.elementAt(position).userName
        holder.location.text = orders.elementAt(position).location
        holder.payment.text = orders.elementAt(position).budget.toString()

        if(orders.elementAt(position).cleaner == ""){
            holder.accept_text.visibility = View.GONE
        }else{
            holder.accept.visibility = View.GONE
        }


    }
}




class CleanerCustomViewHolderOrderList(val view: View, private val findNavController: NavController): RecyclerView.ViewHolder(view){
    val username: TextView =    itemView.findViewById(R.id.username_id)
    val location: TextView = itemView.findViewById(R.id.order_location_id)
    val payment:TextView = itemView.findViewById(R.id.payment_for_order)
    val accept:Button = itemView.findViewById(R.id.accept_order)
    val accept_text:TextView = itemView.findViewById(R.id.order_accepted)



    init{
        view.setOnClickListener{
            findNavController.navigate(R.id.action_CleanerHomePageFragment_to_AddPaymentFragment, Bundle().apply {
                putString("username",username.text.toString())
            })

        }

        accept.setOnClickListener {
              val user = UserDetails().getUserDetails()
              val uName = user?.get(3).toString()

            var db = FirebaseFirestore.getInstance()
            db.collection("orders").get().addOnCompleteListener {
                if (it.isSuccessful) {


                    it.result!!.forEach { q ->
                        var orderName = q.get("userName").toString()

                        if (orderName == username.text.toString()) {
                            var houseInformations = q.get("houseInformation")
                            val details = Gson().toJson(houseInformations)
                            val accessDetails =
                                Gson().fromJson(details, HouseInformation::class.java)
                            var date = q.get("date").toString()
                            var budget = q.get("budget").toString().toInt()
                            var location = q.get("location").toString()


                            var rooms = accessDetails.rooms.toString()
                            var bathrooms = accessDetails.bathrooms.toString()
                            var kitchens = accessDetails.kitchens.toString()
                            var mainfoors = accessDetails.mainFloors.toString()
                            var koridows = accessDetails.koridows.toString()
                            var garage = accessDetails.garages.toString()
                            var typeOfFloors = accessDetails.typeOfFloor.toString()

                            var houseInformation: HouseInformation = HouseInformation(
                                rooms.toInt(), bathrooms.toInt(), kitchens.toInt(),
                                mainfoors.toInt(), koridows.toInt(), garage.toInt(), typeOfFloors
                            )


                            var order = Order(
                                orderName,
                                location,
                                date,
                                uName,
                                budget.toInt(),
                                houseInformation
                            )
                            FirebaseOperations().acceptOrder(order, orderName)

                        }


                    }


                }
            }
        }
    }
}


