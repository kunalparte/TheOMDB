package com.example.kunalparte.theomdb.Movies.model

import android.os.Parcel
import android.os.Parcelable

data class MoviesList(var Response : String) : Parcelable {

    var Search : List<Movie> = emptyList()
    var totalResults : String = ""

    constructor(parcel: Parcel) : this(parcel.readString()) {
        Search = parcel.createTypedArrayList(Movie)
        totalResults = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Response)
        parcel.writeTypedList(Search)
        parcel.writeString(totalResults)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MoviesList> {
        override fun createFromParcel(parcel: Parcel): MoviesList {
            return MoviesList(parcel)
        }

        override fun newArray(size: Int): Array<MoviesList?> {
            return arrayOfNulls(size)
        }
    }

}