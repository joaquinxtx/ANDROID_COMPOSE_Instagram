package com.example.instagramjetpack.search.domain.model

import com.example.instagramjetpack.search.data.source.remote.dto.Location
import com.example.instagramjetpack.search.data.source.remote.dto.Origin

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val origin: Origin,
    val location: Location,
    val image: String
)
