package com.robosoft.foursquare.model.dataclass.individualhotel

data class getParticularPlaceDetailsResponse(
    val _id: String,
    val address: String,
    val location: Location,
    val overview: String,
    val phoneNumber: String,
    val placeImages: PlaceImages,
    val placeName: String,
    val keywords: String,
    val totalrating: String
)