package com.loguito.clase5.models

import android.os.Parcel
import android.os.Parcelable

data class Mail(
    val name: String,
    val email: String,
    val pictureUrl: String,
    val isFavorite: Boolean
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString()?: "",
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(email)
        parcel.writeString(pictureUrl)
        parcel.writeByte(if (isFavorite) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Mail> {
        override fun createFromParcel(parcel: Parcel): Mail {
            return Mail(parcel)
        }

        override fun newArray(size: Int): Array<Mail?> {
            return arrayOfNulls(size)
        }
    }
}