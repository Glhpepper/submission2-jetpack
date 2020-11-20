package com.example.moviescatalogue.data.local.entity

data class DetailEntity(
    val detailId: String,
    val detailTitle: String,
    val detailImage: String,
    val detailOverview: String,
    val detailDirector: String,
    val detailContentScore: String,
    val detailContentScoreText: String,
    val detailTopCast: List<DetailTopCastEntity>?
)

data class DetailTopCastEntity(
    val detailTopCastImg: String,
    val detailTopCastName: String,
    val detailTopCastCharacter:String
)

