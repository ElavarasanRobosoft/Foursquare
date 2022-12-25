package com.robosoft.foursquare.model.dataclass.photoReview

data class ReviewX(
    val _id: String,
    val createdOn: String,
    val review: String,
    val reviewImage: ReviewImageX,
    val userId: UserIdX
)