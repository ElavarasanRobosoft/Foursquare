package com.robosoft.foursquare.model.dataclass.toppick

data class ToppickResponseItem(
    val _id: String,
    val address: String,
    val overview: String,
    val phoneNumber: String,
    val placeImages: PlaceImages,
    val placeName: String,
    val totalrating: String
)