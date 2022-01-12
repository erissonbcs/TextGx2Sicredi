package com.estudoandroid.testgx2sicredi.models

data class Event(
    var date: Long,
    var description: String,
    var image: String,
    var longitude: Double,
    var latitude: Double,
    var price: Float,
    var title: String,
    var id: String
)