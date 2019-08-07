package com.taz.cureous.globalmodels

import android.os.Parcel
import android.os.Parcelable

data class Symptom(val ID: Int, val Name: String, var isSelected: Boolean = false) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(ID)
        parcel.writeString(Name)
        parcel.writeByte(if (isSelected) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Symptom> {
        override fun createFromParcel(parcel: Parcel): Symptom {
            return Symptom(parcel)
        }

        override fun newArray(size: Int): Array<Symptom?> {
            return arrayOfNulls(size)
        }
    }

}