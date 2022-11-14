package com.example.hirehousecleaners.model

data class User(
    var userName:String, var firstName:String,
    var lastName:String,
    var location:String,
    var password:String,
    var role:String,
    var houseInformation: HouseInformation
)
