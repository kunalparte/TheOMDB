package com.example.kunalparte.theomdb.Movies.model

import android.os.Parcel
import android.os.Parcelable

data class Movie(var imdbID : String) : Parcelable {

    var Year : String = ""
    var Title : String = ""
    var Type : String = ""
    var Poster : String = ""
    var Released : String = ""
    var Runtime : String = ""
    var Genre : String = ""
    var Director : String = ""
    var Actors : String = ""
    var Production : String = ""
    var imdbRating : String = ""
    var Language : String = ""
    var Plot : String = ""

    constructor(parcel: Parcel) : this(parcel.readString()) {
        Year = parcel.readString()
        Title = parcel.readString()
        Type = parcel.readString()
        Poster = parcel.readString()
        Released = parcel.readString()
        Runtime = parcel.readString()
        Genre = parcel.readString()
        Director = parcel.readString()
        Actors = parcel.readString()
        Production = parcel.readString()
        imdbRating = parcel.readString()
        Language = parcel.readString()
        Plot = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(imdbID)
        parcel.writeString(Year)
        parcel.writeString(Title)
        parcel.writeString(Type)
        parcel.writeString(Poster)
        parcel.writeString(Released)
        parcel.writeString(Runtime)
        parcel.writeString(Genre)
        parcel.writeString(Director)
        parcel.writeString(Actors)
        parcel.writeString(Production)
        parcel.writeString(imdbRating)
        parcel.writeString(Language)
        parcel.writeString(Plot)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}