package com.example.schoolapplication.data

import com.google.gson.annotations.SerializedName

data class SchoolSatScores(
    @SerializedName("school_name")
    val name : String?,
    @SerializedName("sat_critical_reading_avg_score")
    val satCriticalReadingAvgScore : Int?,
    @SerializedName("sat_math_avg_score")
    val satMathAvgScore : Int?,
    @SerializedName("sat_writing_avg_score")
    val satWritingAvgScore : Int?
)
