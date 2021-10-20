package ua.edmko.data.local.entities

import com.google.gson.annotations.SerializedName

abstract class DaoModel {
   @SerializedName("id") val id: String =""
}