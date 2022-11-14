package com.example.hirehousecleaners.model

data class Order(var userName:String,var location:String, var date:String,var cleaner:String,var budget:Int,var houseInformation: HouseInformation)
