package com.example.schoolapplication.data

import com.google.gson.annotations.SerializedName

data class School(
    @SerializedName("school_name")
    val name : String?
)