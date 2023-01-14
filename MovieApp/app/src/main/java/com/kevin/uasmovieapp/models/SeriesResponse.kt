package com.kevin.uasmovieapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SeriesResponse(
    @SerializedName("results")
    val series : List<Series>

) : Parcelable {
    constructor() : this(mutableListOf())
}