package Model


import android.os.Parcel
import android.os.Parcelable

data class ternak(
    var nama:String?,
    var usia:Int?,
    var jenis:String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()
    ) {
    }
    var imageternak: String = ""

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nama)
        parcel.writeValue(usia)
        parcel.writeString(jenis)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ternak> {
        override fun createFromParcel(parcel: Parcel): ternak {
            return ternak(parcel)
        }

        override fun newArray(size: Int): Array<ternak?> {
            return arrayOfNulls(size)
        }
    }
}