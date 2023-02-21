package com.example.movies_app.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val title: String,
    val imageUrl: String,
    val evaluation: String,
    val synopsis: String,
    val data: String,
    val popularity: String
) : Parcelable