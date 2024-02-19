package com.example.schoolapplication.repository.data

data class SchoolWrapper(
    // represents the union of the school name data set and the sat score data set
    // both of which have school names as parameters
    var name : String?,
    var satCriticalReadingAvgScore : Int?,
    var satMathAvgScore : Int?,
    var satWritingAvgScore : Int?
)